package com.edu.transplantdataapi.controller;


import com.edu.transplantdataapi.dto.analysis.ChiSquareTestParameters;
import com.edu.transplantdataapi.exceptions.InvalidClassFactorNameException;
import com.edu.transplantdataapi.exceptions.InvalidFactorNameException;
import com.edu.transplantdataapi.service.transplantdata.SurvivalResultManager;
import com.edu.transplantdataapi.service.analysis.ChiSquareManager;
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

    @GetMapping("api/survivalResult/histogram")
    public ResponseEntity<?> getHistogramData(
            @RequestParam String factor,
            @RequestParam String classFactor
    ) {
        try {
            return ResponseEntity
                    .ok(survivalResultsManager
                            .getHistogramData(factor, classFactor));
        } catch (InvalidFactorNameException | InvalidClassFactorNameException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("api/survivalResult/chisquaretest")
    public ResponseEntity<?> getChiSquareTestResult(
            @RequestBody ChiSquareTestParameters parameters
            ) {
        return ResponseEntity.ok(chiSquareManager.getChiSquareTestResult(parameters));
    }
}
