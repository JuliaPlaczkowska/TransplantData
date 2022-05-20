package com.edu.transplantdataapi.service.prediction;

import com.edu.transplantdataapi.dto.prediction.TransplantToPredictDto;
import com.edu.transplantdataapi.exceptions.ClassificationTreeException;
import com.edu.transplantdataapi.ml.ClassificationTreeAlgorithm;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import static com.edu.transplantdataapi.utils.ArffUtils.TRANSPLANT_DATA_ARFF;

@Service
public class ClassificationTreeAlgorithmManager {

    private ClassificationTreeAlgorithm weka;
    private final String arffTrain =
            "src/main/resources/dataset/bone-marrow.arff";
    private final String arffTest =
            "src/main/resources/dataset/bone-marrow-test.arff";
    private final String arffToPredict =
            "src/main/resources/dataset/bone-marrow-to-predict.arff";

    public List<String> tree() {
        List<String> result;
        try {
            weka =
                    new ClassificationTreeAlgorithm(arffTrain, arffTest);
            result = weka.getTree();
        } catch (Exception e) {
            throw new ClassificationTreeException();
        }
        return result;
    }

    public List<String> evaluationSummary() {
        List<String> result;
        try {
            weka =
                    new ClassificationTreeAlgorithm(arffTrain, arffTest);
            result = weka.evaluationSummary();
        } catch (Exception e) {
            throw new ClassificationTreeException();
        }
        return result;
    }

    public String predict(TransplantToPredictDto transplant) {
        String result;
        try {
            writeTransplantToPredictDtoToArffFile(transplant);
            weka =
                    new ClassificationTreeAlgorithm(arffTrain, arffToPredict);
            result = weka.predict();

        } catch (Exception e) {
            e.printStackTrace();
            return "Blad";
        }
        return result;
    }

    private void writeTransplantToPredictDtoToArffFile(TransplantToPredictDto transplant)
            throws IOException {
        PrintWriter writer = new PrintWriter(arffToPredict, StandardCharsets.UTF_8);
        writer.print(TRANSPLANT_DATA_ARFF);

        String[] entries =
                new String[]
                        {
                                String.valueOf(transplant.getDonorAge()),
                                transplant.getDonorBloodABO(),
                                transplant.getDonorPresenceOfCMV(),
                                String.valueOf(transplant.getRecipientAge()),
                                transplant.getRecipientBloodABO(),
                                transplant.getRecipientPresenceOfCMV(),
                                transplant.getDisease(),
                                transplant.getDiseaseGroup(),
                                transplant.getMatchHLA(),
                                transplant.getMismatchHLA(),
                                String.valueOf(transplant.getAntigen()),
                                String.valueOf(transplant.getAllele()),
                                transplant.getGroup1HLA(),
                                transplant.getRiskGroup(),
                                transplant.getStemCellSource(),
                                transplant.getPostRelapse(),
                                String.valueOf(transplant.getCD34perKg()),
                                String.valueOf(transplant.getCD3perKg()),
                                "1"
                        };
        Arrays.stream(entries).forEach(element -> writer.print(element + ","));
        writer.close();
    }
}
