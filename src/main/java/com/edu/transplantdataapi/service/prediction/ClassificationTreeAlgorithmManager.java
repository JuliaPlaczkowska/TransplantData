package com.edu.transplantdataapi.service.prediction;

import com.edu.transplantdataapi.ml.ClassificationTreeAlgorithm;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClassificationTreeAlgorithmManager {

    private ClassificationTreeAlgorithm weka;

    public String tree1(String arff1) {
        String result;

        try {
            weka =
                    new ClassificationTreeAlgorithm(arff1);
            result = weka.buildDecisionTree1();

        } catch (Exception e) {
            e.printStackTrace();
            return "Blad";
        }
        return result;
    }

    public List<String> tree2(String arff1, String arff2) {
        List<String> result;

        try {
            weka =
                    new ClassificationTreeAlgorithm(
                            arff1, arff2
                    );
            result = weka.buildDecisionTree2();

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
        return result;
    }

    public List<String> evaluationSummary(String arff1,
                                   String arff2) {
        List<String> result;

        try {
            weka =
                    new ClassificationTreeAlgorithm(
                            arff1, arff2
                    );
            result = weka.evaluationSummary();

        } catch (Exception e) {
            e.printStackTrace();
            return List.of("Blad");
        }
        return result;
    }

    public String tree4(String arff1, String arff2) {
        String result;

        try {
            weka =
                    new ClassificationTreeAlgorithm(
                            arff1, arff2
                    );
            result = weka.buildDecisionTree4();

        } catch (Exception e) {
            e.printStackTrace();
            return "Blad";
        }
        return result;
    }

}
