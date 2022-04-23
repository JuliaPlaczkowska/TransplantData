package com.edu.transplantdataapi.entity.analysis;

import com.edu.transplantdataapi.entity.transplantdata.SurvivalResult;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnalysisResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String factor;
    private String classFactor;

    @OneToMany(mappedBy = "analysisResult", cascade = CascadeType.ALL)
    private List<SurvivalResult> dataset;

    @ManyToOne
    private Analysis analysis;

    public AnalysisResult(String factor, String classFactor, List<SurvivalResult> dataset) {
        this.factor = factor;
        this.classFactor = classFactor;
        this.dataset = dataset;
    }
}
