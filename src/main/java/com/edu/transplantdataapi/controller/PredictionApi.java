package com.edu.transplantdataapi.controller;

import com.edu.transplantdataapi.dto.prediction.TransplantToPredictDto;
import com.edu.transplantdataapi.service.prediction.ClassificationTreeAlgorithmManager;
import com.edu.transplantdataapi.service.transplantdata.TransplantManager;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prediction")
@CrossOrigin
@AllArgsConstructor
public class PredictionApi {

    private ClassificationTreeAlgorithmManager classificationTreeAlgorithmManager;
    private TransplantManager transplantManager;

    @GetMapping("/tree")
    public ResponseEntity<?> generateClassificationTree() {
        return ResponseEntity.ok(classificationTreeAlgorithmManager.tree());
    }

    @GetMapping("/summary")
    public ResponseEntity<?> getEvaluationSummary() {
        return ResponseEntity.ok(classificationTreeAlgorithmManager.evaluationSummary());
    }

    @GetMapping("/predict")
    public ResponseEntity<?> predict(@RequestBody TransplantToPredictDto transplantDto) {
        return ResponseEntity.ok(classificationTreeAlgorithmManager.predict(transplantDto));
    }

    @GetMapping("/transplants")
    public ResponseEntity<?> getTransplantList() {
        return ResponseEntity.ok(transplantManager.getAllAsTransplantToPredictDto());
    }
}
