package com.edu.transplantdataapi.service.prediction;

import com.edu.transplantdataapi.ml.ClassificationTreeAlgorithm;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClassificationTreeAlgorithmManager {

    private ClassificationTreeAlgorithm weka;
    private final String arrfTrain =
            "src/main/resources/dataset/bone-marrow.arff";
    private final String arrfTest =
            "src/main/resources/dataset/bone-marrow-test.arff";

    public List<String> tree() {
        List<String> result;

        try {
            weka =
                    new ClassificationTreeAlgorithm(arrfTrain, arrfTest);
            result = weka.getTree();

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
        return result;
    }

    public List<String> evaluationSummary() {
        List<String> result;

        try {
            weka =
                    new ClassificationTreeAlgorithm(arrfTrain, arrfTest);
            result = weka.evaluationSummary();

        } catch (Exception e) {
            e.printStackTrace();
            return List.of("Blad");
        }
        return result;
    }

    public String predict() {
        String result;

        try {
            weka =
                    new ClassificationTreeAlgorithm(arrfTrain, arrfTest);
            result = weka.predict();

        } catch (Exception e) {
            e.printStackTrace();
            return "Blad";
        }
        return result;
    }
}
