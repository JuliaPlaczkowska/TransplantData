package com.edu.transplantdataapi.business.service.prediction;

import com.edu.transplantdataapi.business.ml.ClassificationTreeAlgorithm;
import com.edu.transplantdataapi.dto.prediction.PredictionResultDto;
import com.edu.transplantdataapi.dto.transplantdata.TransplantDto;
import com.edu.transplantdataapi.exceptions.ClassificationTreeException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import static com.edu.transplantdataapi.business.utils.ArffUtils.TRANSPLANT_DATA_ARFF;

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

    public PredictionResultDto predict(TransplantDto transplant) {
        PredictionResultDto result;
        try {
            writeTransplantToPredictDtoToArffFile(transplant);
            weka =
                    new ClassificationTreeAlgorithm(arffTrain, arffToPredict);
            result = weka.predict();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(
                    "Błąd podczas generowania drzewa klasyfikacyjnego. Sprawdź poprawność wprowadznych danych.");
        }
        return result;
    }

    private void writeTransplantToPredictDtoToArffFile(TransplantDto transplant)
            throws IOException {
        PrintWriter writer = new PrintWriter(arffToPredict, StandardCharsets.UTF_8);
        writer.print(TRANSPLANT_DATA_ARFF);

        String[] entries = transplantDtoToArffEntries(transplant);

        Arrays.stream(entries).forEach(element -> writer.print(element + ","));
        writer.close();
    }

    private String[] transplantDtoToArffEntries(TransplantDto transplant) {
        return new String[]
                {
                        String.valueOf(transplant.getDonor().getPatient().getAge()),
                        transplant.getDonor().getPatient().getBloodABO(),
                        transplant.getDonor().getPatient().getPresenceOfCMV(),
                        String.valueOf(transplant.getRecipient().getPatient().getAge()),
                        transplant.getRecipient().getPatient().getBloodABO(),
                        transplant.getRecipient().getPatient().getPresenceOfCMV(),
                        transplant.getRecipient().getDisease(),
                        transplant.getRecipient().getDiseaseGroup(),
                        transplant.getMatchHLA() + "/10",
                        transplant.isMismatchHLA() ? "matched" : "mismatched",
                        String.valueOf(transplant.getAntigen()),
                        String.valueOf(transplant.getAllele()),
                        transplant.getGroup1HLA(),
                        transplant.getRecipient().getRiskGroup(),
                        transplant.getDonor().getStemCellSource(),
                        transplant.isPostRelapse() ? "yes" : "no",
                        String.valueOf(transplant.getCD34perKg()),
                        String.valueOf(transplant.getCD3perKg()),
                        "1"
                };
    }
}
