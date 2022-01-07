package com.edu.transplantdataapi.entity.prediction;


import com.edu.transplantdataapi.entity.Transplant;

import javax.persistence.*;

@Entity
public class Prediction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private PredictionTree predictionTree;

    @OneToOne
    private Transplant transplant;

    private String classificationResult;
    private double accuracy;


    public Prediction(PredictionTree predictionTree, Transplant transplant, String classificationResult, double accuracy) {
        this.predictionTree = predictionTree;
        this.transplant = transplant;
        this.classificationResult = classificationResult;
        this.accuracy = accuracy;
    }

    public Prediction() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public PredictionTree getPredictionTree() {
        return predictionTree;
    }

    public void setPredictionTree(PredictionTree predictionTree) {
        this.predictionTree = predictionTree;
    }

    public Transplant getTransplant() {
        return transplant;
    }

    public void setTransplant(Transplant transplant) {
        this.transplant = transplant;
    }

    public String getClassificationResult() {
        return classificationResult;
    }

    public void setClassificationResult(String classificationResult) {
        this.classificationResult = classificationResult;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }
}
