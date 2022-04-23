package com.edu.transplantdataapi.entity.prediction;

import com.edu.transplantdataapi.datatransferobject.prediction.TransplantDto;
import com.edu.transplantdataapi.ml.ClassificationTreeAlgorithm;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import weka.classifiers.trees.J48;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class PredictionTree {

    //może do bazy zapisywać tylko wybrane rzeczy

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

            // weka.visualizeTree(tree);
            result = weka.getTree();
        } catch (Exception e) {

            e.printStackTrace();
        }
        return result;
    }

    public String predict(TransplantDto transplant){

        String classificationResult = "";

        try {
            ClassificationTreeAlgorithm weka =
                    new ClassificationTreeAlgorithm(
                            "src/main/resources/dataset/bone-marrow.arff"
                    );
            weka.selectFeatures();
            weka.buildDecisionTree();

            //classificationResult = weka.classifyData(transplant);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return classificationResult;
    }

}
