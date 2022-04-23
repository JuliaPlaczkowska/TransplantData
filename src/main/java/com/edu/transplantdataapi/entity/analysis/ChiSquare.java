package com.edu.transplantdataapi.entity.analysis;

import com.edu.transplantdataapi.datatransferobject.analysis.HistogramDatasetDto;
import com.edu.transplantdataapi.entity.transplantdata.SurvivalResult;
import lombok.*;
import org.apache.commons.math3.stat.inference.ChiSquareTest;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChiSquare extends AnalysisResult {

    @ElementCollection
    private List<Double> expected;
    @ElementCollection
    private List<Long> observed;

    private double significance;

    private double pValue;
    private boolean rejected;

    public ChiSquare(String factor,
                     String classFactor,
                     List<SurvivalResult> dataset,
                     double significance) {
        super(factor, classFactor, dataset);
        this.significance = significance;
        generateObservedExpected();
        this.pValue = calculatePValue();
        this.rejected = calculateReject();
    }

    private void generateObservedExpected(){

      HistogramDatasetDto histogramDatasetDto =
              new HistogramDatasetDto(this.getFactor(), this.getClassFactor(),this.getDataset());

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

        this.setObserved(observedArrayList);
        this.setExpected(expectedArrayList);
    }

    private double calculatePValue(){
        return new ChiSquareTest()
                .chiSquareTest(
                listToTableDouble(expected),
                listToTableLong(observed)
        );
    }

    private boolean calculateReject(){
        return new ChiSquareTest()
                .chiSquareTest(
                listToTableDouble(expected),
                listToTableLong(observed),
                significance
        );
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
