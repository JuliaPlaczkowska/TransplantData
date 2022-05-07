package com.edu.transplantdataapi.controller;

import com.edu.transplantdataapi.service.prediction.ClassificationTreeAlgorithmManager;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/prediction")
@CrossOrigin
@AllArgsConstructor
public class PredictionApi {

    private ClassificationTreeAlgorithmManager classificationTreeAlgorithmManager;


    @GetMapping("/tree")
    public List<String> generateClassificationTree() {
        return classificationTreeAlgorithmManager.tree();
    }

    @GetMapping("/summary")
    public List<String> getEvaluationSummary() {
        return classificationTreeAlgorithmManager.evaluationSummary();
    }

    @GetMapping("/predict")
    public String predict() {
        return classificationTreeAlgorithmManager.predict();
    }

//    private TransplantDto parseJsonTransplant(String transplant){
//
//        JSONObject transplantJSON = new JSONObject(transplant);
//        JSONObject recipientJSON = transplantJSON.getJSONObject("recipient");
//        JSONObject recipientPatientJSON = recipientJSON.getJSONObject("patient");
//        JSONObject donorJSON = transplantJSON.getJSONObject("donor");
//        JSONObject donorPatientJSON = donorJSON.getJSONObject("patient");
//
//        RecipientDto recipientDto = new RecipientDto(
//                parseJsonPatient(recipientPatientJSON).convertToPatient(),
//                recipientJSON.getString("bloodRh"),
//                recipientJSON.getDouble("bodyMass"),
//                recipientJSON.getString("disease"),
//                recipientJSON.getString("diseaseGroup"),
//                recipientJSON.getString("riskGroup")
//        );
//
//        DonorDto donorDto = new DonorDto(
//                parseJsonPatient(donorPatientJSON).convertToPatient(),
//                //donorJSON.getString("stemCellSource")
//                "TODO"
//        );
//
//        return new TransplantDto(
//                recipientDto,
//                donorDto,
//                transplantJSON.getInt("matchHLA"),
//                transplantJSON.getBoolean("mismatchHLA"),
//                transplantJSON.getInt("antigen"),
//                transplantJSON.getInt("allele"),
//                transplantJSON.getString("group1HLA"),
//                transplantJSON.getBoolean("postRelapse"),
//                transplantJSON.getDouble("cd34perKg"),
//                transplantJSON.getDouble("cd3perKg")
//        );
//    }
//
//    private PatientDto parseJsonPatient(JSONObject patientJson){
//        return new PatientDto(
//                patientJson.getInt("number"),
//                patientJson.getDouble("age"),
//                patientJson.getString("bloodABO"),
//                patientJson.getString("presenceOfCMV")
//        );
//    }

}
