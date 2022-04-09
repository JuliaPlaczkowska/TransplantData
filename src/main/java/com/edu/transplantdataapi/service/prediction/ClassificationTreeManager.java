package com.edu.transplantdataapi.service.prediction;

import com.edu.transplantdataapi.ml.ClassificationTreeAlgorithm;
import org.springframework.stereotype.Service;

@Service
public class ClassificationTreeManager {

    public String getTree(String arff){
        String result;
        try{
            ClassificationTreeAlgorithm classificationTree = new ClassificationTreeAlgorithm(arff);
            result = classificationTree.buildDecisionTree();
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }

        return result;
    }

}
