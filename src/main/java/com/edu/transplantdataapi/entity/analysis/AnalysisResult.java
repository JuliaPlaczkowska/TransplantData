package com.edu.transplantdataapi.entity.analysis;

import com.edu.transplantdataapi.entity.SurvivalResult;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

@Entity
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

    public AnalysisResult() {
    }

    public AnalysisResult(String factor, String classFactor, List<SurvivalResult> dataset) {
        this.factor = factor;
        this.classFactor = classFactor;
        this.dataset = dataset;
    }

    public List<Double> boxedDoubleToList(double[] boxed){
        return DoubleStream.of(boxed).boxed().collect(Collectors.toList());

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getFactor() {
        return factor;
    }

    public void setFactor(String factor) {
        this.factor = factor;
    }

    public String getClassFactor() {
        return classFactor;
    }

    public void setClassFactor(String classFactor) {
        this.classFactor = classFactor;
    }

    public List<SurvivalResult> getDataset() {
        return dataset;
    }

    public void setDataset(List<SurvivalResult> dataset) {
        this.dataset = dataset;
    }
}
