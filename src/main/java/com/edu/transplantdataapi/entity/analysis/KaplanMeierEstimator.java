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
}
