package com.edu.transplantdataapi.presentation.controller;

import com.edu.transplantdataapi.business.service.prediction.ClassificationTreeAlgorithmManager;
import com.edu.transplantdataapi.dto.transplantdata.TransplantDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prediction")
@CrossOrigin
@AllArgsConstructor
public class
PredictionApi {

    private ClassificationTreeAlgorithmManager classificationTreeAlgorithmManager;

    @GetMapping("/tree")
    public ResponseEntity<?> generateClassificationTree() {
        return ResponseEntity.ok(classificationTreeAlgorithmManager.tree());
    }

    @GetMapping("/summary")
    public ResponseEntity<?> getEvaluationSummary() {
        return ResponseEntity.ok(classificationTreeAlgorithmManager.evaluationSummary());
    }

    @PostMapping("/predict")
    public ResponseEntity<?> predict(@RequestBody TransplantDto transplantDto) {
        return ResponseEntity.ok(classificationTreeAlgorithmManager.predict(transplantDto));
    }
}
