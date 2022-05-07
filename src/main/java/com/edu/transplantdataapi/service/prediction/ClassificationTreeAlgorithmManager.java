package com.edu.transplantdataapi.service.prediction;

import com.edu.transplantdataapi.dto.prediction.TransplantToPredictDto;
import com.edu.transplantdataapi.ml.ClassificationTreeAlgorithm;
import com.opencsv.CSVWriter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ClassificationTreeAlgorithmManager {

    private ModelMapper modelMapper = new ModelMapper();
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
            e.printStackTrace();
            return new ArrayList<>();
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
            e.printStackTrace();
            return List.of("Blad");
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
        writer.print("@relation 'bone-marrow\n" +
                "\n" +
                "@attribute donor_age numeric\n" +
                "@attribute donor_ABO {0,A,AB,B}\n" +
                "@attribute donor_CMV {absent,present}\n" +
                "@attribute recipient_age numeric\n" +
                "@attribute recipient_ABO {0,A,AB,B}\n" +
                "@attribute recipient_CMV {absent,present}\n" +
                "@attribute disease {ALL,AML,chronic,lymphoma,nonmalignant}\n" +
                "@attribute disease_group {malignant,nonmalignant}\n" +
                "@attribute HLA_match {10/10,7/10,8/10,9/10}\n" +
                "@attribute HLA_mismatch {matched,mismatched}\n" +
                "@attribute antigen {0,1,2,3}\n" +
                "@attribute allel {0,1,2,3,4}\n" +
                "@attribute HLA_group_1 {DRB1_cell,matched,mismatched,one_allel,one_antigen,three_diffs,two_diffs}\n" +
                "@attribute risk_group {high,low}\n" +
                "@attribute stem_cell_source {bone_marrow,peripheral_blood}\n" +
                "@attribute tx_post_relapse {no,yes}\n" +
                "@attribute CD34_x1e6_per_kg numeric\n" +
                "@attribute CD3_x1e8_per_kg numeric\n" +
                "@attribute survival_status {0,1}\n" +
                "\n" +
                "@data"+
                "\n");

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
        Arrays.stream(entries).forEach(element -> writer.print(element+","));
        writer.close();
    }
}
