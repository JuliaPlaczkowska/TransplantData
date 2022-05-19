package com.edu.transplantdataapi.controller;

import com.edu.transplantdataapi.dto.prediction.TransplantToPredictDto;
import com.edu.transplantdataapi.service.transplantdata.TransplantManager;
import com.edu.transplantdataapi.service.prediction.ClassificationTreeAlgorithmManager;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prediction")
@CrossOrigin
@AllArgsConstructor
public class PredictionApi {

    private ClassificationTreeAlgorithmManager classificationTreeAlgorithmManager;
    private TransplantManager transplantManager;

    @GetMapping("/tree")
    public List<String> generateClassificationTree() {
        return classificationTreeAlgorithmManager.tree();
    }

    @GetMapping("/summary")
    public List<String> getEvaluationSummary() {
        return classificationTreeAlgorithmManager.evaluationSummary();
    }

    @GetMapping("/predict")
    public String predict(@RequestBody TransplantToPredictDto transplantDto) {
        return classificationTreeAlgorithmManager.predict(transplantDto);
    }

    @GetMapping("/transplants")
    public List<TransplantToPredictDto> getTransplantList(){
        return transplantManager.getAllAsTransplantToPredictDto();
    }
}
