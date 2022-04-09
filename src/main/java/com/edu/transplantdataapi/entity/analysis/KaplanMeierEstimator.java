package com.edu.transplantdataapi.entity.analysis;

import com.edu.transplantdataapi.entity.transplantdata.SurvivalResult;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class KaplanMeierEstimator extends AnalysisResult {

    @ElementCollection
    private List<Double> ti;

    @ElementCollection
    private List<Double> di;

    @ElementCollection
    private List<Double> ni;

    @ElementCollection
    private List<Double> st;

    public KaplanMeierEstimator(String factor, String classFactor, ArrayList<SurvivalResult> dataset, List<Double> ti, List<Double> di, List<Double> ni, List<Double> st) {
        super(factor, classFactor, dataset);
        this.ti = ti;
        this.di = di;
        this.ni = ni;
        this.st = st;
    }

    public KaplanMeierEstimator(String factor, String classFactor, ArrayList<SurvivalResult> dataset, double[] ti, double[] di, double[] ni, double[] st) {
        super(factor, classFactor, dataset);
        this.ti = boxedDoubleToList(ti);
        this.di = boxedDoubleToList(di);
        this.ni = boxedDoubleToList(ni);
        this.st = boxedDoubleToList(st);
    }

}
