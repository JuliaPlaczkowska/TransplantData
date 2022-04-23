package com.edu.transplantdataapi.service.analysis;

import com.edu.transplantdataapi.datatransferobject.analysis.HistogramDatasetDto;
import com.edu.transplantdataapi.entity.analysis.ChiSquare;
import com.edu.transplantdataapi.repository.analysis.ChiSquareRepo;
import org.apache.commons.math3.stat.inference.ChiSquareTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChiSquareManager {

    public void generateObservedExpected(ChiSquare chiSquare){

        HistogramDatasetDto histogramDatasetDto =
                new HistogramDatasetDto(chiSquare.getFactor(), chiSquare.getClassFactor(),chiSquare.getDataset());

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
        double observedSum=0;

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
                expected[i][j] = (sumRows[i]*sumCols[j])/observedSum;
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

    public double calculatePValue(ChiSquare chiSquare){
        double pValue = new ChiSquareTest()
                .chiSquareTest(
                        listToTableDouble(chiSquare.getExpected()),
                        listToTableLong(chiSquare.getObserved())
                );
        chiSquare.setPValue(pValue);
        return pValue;
    }

    public boolean calculateReject(ChiSquare chiSquare){
        boolean reject = new ChiSquareTest()
                .chiSquareTest(
                        listToTableDouble(chiSquare.getExpected()),
                        listToTableLong(chiSquare.getObserved()),
                        chiSquare.getSignificance()
                );
        chiSquare.setRejected(reject);
        return reject;
    }

    private double[] listToTableDouble(List<Double> list){
        double[] expectedArray = new double[list.size()];
        for(int i = 0; i < list.size(); i++)
            expectedArray[i] = list.get(i);
        return expectedArray;
    }

    private long[] listToTableLong(List<Long> list){
        long[] expectedArray = new long[list.size()];
        for(int i = 0; i < list.size(); i++)
            expectedArray[i] = list.get(i);
        return expectedArray;
    }
}
