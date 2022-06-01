package com.edu.transplantdataapi.presentation.controller;

import com.edu.transplantdataapi.dto.analysis.ChiSquareTestParameters;
import com.edu.transplantdataapi.business.service.analysis.ChiSquareManager;
import com.edu.transplantdataapi.business.service.transplantdata.SurvivalResultManager;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@CrossOrigin
@AllArgsConstructor
public class AnalysisApi {

    private SurvivalResultManager survivalResultsManager;
    private ChiSquareManager chiSquareManager;

    @GetMapping("api/survival-result/histogram")
    public ResponseEntity<?> getHistogramData(
            @RequestParam String factor,
            @RequestParam String classFactor
    ) {
        return ResponseEntity
                .ok(survivalResultsManager
                        .getHistogramData(factor, classFactor));
    }

    @PostMapping("api/survival-result/chi-square")
    public ResponseEntity<?> getChiSquareTestResult(
            @RequestBody ChiSquareTestParameters parameters
    ) {
        return ResponseEntity.ok(chiSquareManager.getChiSquareTestResult(parameters));
    }
}
