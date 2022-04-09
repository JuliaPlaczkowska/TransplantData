package com.edu.transplantdataapi.controller;

import com.edu.transplantdataapi.datatransferobject.DonorDto;
import com.edu.transplantdataapi.datatransferobject.PatientDto;
import com.edu.transplantdataapi.datatransferobject.RecipientDto;
import com.edu.transplantdataapi.datatransferobject.prediction.PredictionResultDto;
import com.edu.transplantdataapi.datatransferobject.prediction.PredictionTreeDto;
import com.edu.transplantdataapi.datatransferobject.prediction.TransplantDto;
import com.edu.transplantdataapi.entity.prediction.PredictionTree;
import com.edu.transplantdataapi.service.SurvivalResultManager;
import com.edu.transplantdataapi.service.prediction.ClassificationTreeManager;
import com.edu.transplantdataapi.service.prediction.PredictionManager;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api/prediction")
@CrossOrigin
public class PredictionApi {

    private ClassificationTreeManager classificationTreeManager;

    @Autowired
    public PredictionApi(ClassificationTreeManager classificationTreeManager) {
        this.classificationTreeManager = classificationTreeManager;

    }

    @GetMapping("/tree")
    public PredictionTreeDto getPredictionTree(
            //@RequestParam String classFactor
    ) throws Exception {

        PredictionTree predictionTree = new PredictionTree();

        PredictionTreeDto predictionTreeDto = new PredictionTreeDto();
        predictionTreeDto.setTree(classificationTreeManager.getTree("src/main/resources/dataset/bone-marrow.arff"));

        return predictionTreeDto;
    }

    @GetMapping("/predict")
    public PredictionResultDto predict(
            String transplantPredictionDto
    ) throws Exception {

        PredictionTree predictionTree = new PredictionTree();

        PredictionResultDto predictionResultDto = new PredictionResultDto();
        predictionResultDto.setClassifiedAs(
                predictionTree.predict(parseJsonTransplant(transplantPredictionDto))
        );

        return predictionResultDto;
    }


    private TransplantDto parseJsonTransplant(String transplant){

        JSONObject transplantJSON = new JSONObject(transplant);
        JSONObject recipientJSON = transplantJSON.getJSONObject("recipient");
        JSONObject recipientPatientJSON = recipientJSON.getJSONObject("patient");
        JSONObject donorJSON = transplantJSON.getJSONObject("donor");
        JSONObject donorPatientJSON = donorJSON.getJSONObject("patient");

        RecipientDto recipientDto = new RecipientDto(
                parseJsonPatient(recipientPatientJSON).convertToPatient(),
                recipientJSON.getString("bloodRh"),
                recipientJSON.getDouble("bodyMass"),
                recipientJSON.getString("disease"),
                recipientJSON.getString("diseaseGroup"),
                recipientJSON.getString("riskGroup")
        );

        DonorDto donorDto = new DonorDto(
                parseJsonPatient(donorPatientJSON).convertToPatient(),
                //donorJSON.getString("stemCellSource")
                "TODO"
        );

        return new TransplantDto(
                recipientDto,
                donorDto,
                transplantJSON.getInt("matchHLA"),
                transplantJSON.getBoolean("mismatchHLA"),
                transplantJSON.getInt("antigen"),
                transplantJSON.getInt("allele"),
                transplantJSON.getString("group1HLA"),
                transplantJSON.getBoolean("postRelapse"),
                transplantJSON.getDouble("cd34perKg"),
                transplantJSON.getDouble("cd3perKg")
        );
    }

    private PatientDto parseJsonPatient(JSONObject patientJson){
        return new PatientDto(
                patientJson.getInt("number"),
                patientJson.getDouble("age"),
                patientJson.getString("bloodABO"),
                patientJson.getString("presenceOfCMV")
        );
    }

}
