package com.edu.transplantdataapi.entity.analysis;

import com.edu.transplantdataapi.entity.transplantdata.SurvivalResult;
import lombok.*;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

    public List<Double> boxedDoubleToList(double[] boxed){
        return DoubleStream.of(boxed).boxed().collect(Collectors.toList());
    }
}
