package com.edu.transplantdataapi.entity.prediction;

import com.edu.transplantdataapi.datatransferobject.prediction.TransplantPredictionDto;
import com.edu.transplantdataapi.ml.ClassificationTreeAlgorithm;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

import javax.persistence.*;
import java.util.List;

@Entity
public class PredictionTree {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @OneToMany
//    private List<SurvivalResult> dataset;


    @ElementCollection
    private List<String> factors;

    private String classFactor;

    private J48 tree;

    public PredictionTree() throws Exception {
        this.tree = buildDecisionTree();
    }

    public PredictionTree(String classFactor) throws Exception {
        this.classFactor = classFactor;
        this.tree = buildDecisionTree();
    }

    public PredictionTree(List<String> factors, String classFactor) throws Exception {
        this.factors = factors;
        this.classFactor = classFactor;
    }

    public J48 buildDecisionTree() throws Exception {

        J48 result = new J48();

        try {
            ClassificationTreeAlgorithm weka =
                    new ClassificationTreeAlgorithm(
                            "src/main/resources/dataset/bone-marrow.arff"
                    );
            weka.selectFeatures();
            weka.buildDecisionTree();

            J48 tree = weka.getTree();
            result = tree;
        } catch (Exception e) {

            e.printStackTrace();
        }
        return result;
    }

    public String predict(TransplantPredictionDto transplant){

        String classificationResult = "";

        try {
            ClassificationTreeAlgorithm weka =
                    new ClassificationTreeAlgorithm(
                            "src/main/resources/dataset/bone-marrow.arff"
                    );
            weka.selectFeatures();
            weka.buildDecisionTree();

            classificationResult = weka.classifyData(transplant);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return classificationResult;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getClassFactor() {
        return classFactor;
    }

    public void setClassFactor(String classFactor) {
        this.classFactor = classFactor;
    }


    public J48 getTree() {
        return tree;
    }

    public void setTree(J48 tree) {
        this.tree = tree;
    }

    public List<String> getFactors() {
        return factors;
    }

    public void setFactors(List<String> factors) {
        this.factors = factors;
    }
}
