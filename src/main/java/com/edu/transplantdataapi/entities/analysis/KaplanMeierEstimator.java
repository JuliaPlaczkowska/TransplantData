package com.edu.transplantdataapi.entities.analysis;

import lombok.*;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.List;

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
