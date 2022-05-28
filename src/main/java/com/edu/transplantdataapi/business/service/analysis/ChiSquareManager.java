package com.edu.transplantdataapi.business.service.analysis;

import com.edu.transplantdataapi.dto.analysis.ChiSquareTestDto;
import com.edu.transplantdataapi.dto.analysis.ChiSquareTestParameters;
import com.edu.transplantdataapi.dto.analysis.HistogramDatasetDto;
import com.edu.transplantdataapi.entities.analysis.ChiSquare;
import com.edu.transplantdataapi.entities.transplantdata.SurvivalResult;
import com.edu.transplantdataapi.entities.enums.ClassFactor;
import com.edu.transplantdataapi.entities.enums.Factor;
import com.edu.transplantdataapi.integration.repository.transplantdata.SurvivalResultRepo;
import com.edu.transplantdataapi.business.validation.FactorsValidator;
import com.edu.transplantdataapi.business.validation.SignificanceValidator;
import lombok.AllArgsConstructor;
import org.apache.commons.math3.stat.inference.ChiSquareTest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class ChiSquareManager {

    private SurvivalResultRepo survivalResultRepo;
    private HistogramManager histogramManager;

    public ChiSquareTestDto getChiSquareTestResult(ChiSquareTestParameters parameters) {
        validateParameters(parameters);
        List<SurvivalResult> survivalResults = survivalResultRepo.findAll();
        return calculateChiSquareTest(
                parameters,
                survivalResults);
    }

    private void validateParameters(ChiSquareTestParameters parameters) {
        FactorsValidator.validateFactorName(parameters.getFactor());
        FactorsValidator.validateClassFactorName(parameters.getClassFactor());
        SignificanceValidator.validateSignificance(parameters.getSignificance());
    }

    private ChiSquareTestDto calculateChiSquareTest(
            ChiSquareTestParameters parameters,
            List<SurvivalResult> survivalResultList
    ) {
        ChiSquare chiSquare = new ChiSquare(
                parameters.getFactor(),
                parameters.getClassFactor(),
                survivalResultList,
                parameters.getSignificance());

        generateObservedExpected(chiSquare);
        calculatePValue(chiSquare);
        calculateReject(chiSquare);

        return new ChiSquareTestDto(
                chiSquare.getPValue(),
                chiSquare.isRejected()
        );
    }

    private void generateObservedExpected(ChiSquare chiSquare) {

        HistogramDatasetDto histogramDatasetDto =
                histogramManager.getHistogramData(
                        Factor.valueOf(chiSquare.getFactor()),
                        ClassFactor.valueOf(chiSquare.getClassFactor()),
                        chiSquare.getDataset());

        int columns = histogramDatasetDto.getLabels().size();
        int rows = histogramDatasetDto.getDatasets().size();

        long[][] observed = new long[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                observed[i][j] =
                        (
                                histogramDatasetDto
                                        .getDatasets().get(i)
                                        .getData().get(j)
                        ).longValue();
            }
        }

        int[] sumRows = new int[rows];
        int[] sumCols = new int[columns];
        double observedSum = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                sumRows[i] += observed[i][j];
                sumCols[j] += observed[i][j];
                observedSum += observed[i][j];
            }
        }

        double[][] expected = new double[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                expected[i][j] = (sumRows[i] * sumCols[j]) / observedSum;
            }
        }

        ArrayList<Double> expectedArrayList = new ArrayList<>();
        ArrayList<Long> observedArrayList = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                expectedArrayList.add(expected[i][j]);
                observedArrayList.add(observed[i][j]);
            }
        }
        chiSquare.setObserved(observedArrayList);
        chiSquare.setExpected(expectedArrayList);
    }

    private void calculatePValue(ChiSquare chiSquare) {
        double pValue = new ChiSquareTest()
                .chiSquareTest(
                        listToDoubleArray(chiSquare.getExpected()),
                        listToLongArray(chiSquare.getObserved())
                );
        chiSquare.setPValue(pValue);
    }

    private void calculateReject(ChiSquare chiSquare) {
        boolean reject = new ChiSquareTest()
                .chiSquareTest(
                        listToDoubleArray(chiSquare.getExpected()),
                        listToLongArray(chiSquare.getObserved()),
                        chiSquare.getSignificance()
                );
        chiSquare.setRejected(reject);
    }

    private double[] listToDoubleArray(List<Double> list) {
        return list
                .stream()
                .mapToDouble(d -> d)
                .toArray();
    }

    private long[] listToLongArray(List<Long> list) {
        return list
                .stream()
                .mapToLong(l -> l)
                .toArray();
    }
}
