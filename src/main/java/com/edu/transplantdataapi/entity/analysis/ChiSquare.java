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
                     double significance){
        super(factor, classFactor, dataset);
        this.significance = significance;
    }
}
