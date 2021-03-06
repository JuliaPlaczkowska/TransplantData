package com.edu.transplantdataapi.presentation.controller;

import com.edu.transplantdataapi.dto.prediction.SurvivalResultDto;
import com.edu.transplantdataapi.business.service.transplantdata.SurvivalResultManager;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@CrossOrigin
@AllArgsConstructor
public class SurvivalResultApi {

    private SurvivalResultManager survivalResultManager;

    @GetMapping("api/survivalResult/dataGrid")
    public ResponseEntity<?> getSurvivalResultsForDataGrid() {
        return ResponseEntity.ok(survivalResultManager.getSurvivalResultsForDataGrid());
    }

    @GetMapping("api/survivalResult/all")
    public ResponseEntity<?> getSurvivalResultsDto() {
        return ResponseEntity.ok(survivalResultManager.findAllSurvivalResultDto());
    }

    @PostMapping("api/survivalResult")
    public ResponseEntity<?> addSurvivalResult(@RequestBody SurvivalResultDto survivalResult ){
        return  ResponseEntity.ok(survivalResultManager.save(survivalResult));
    }
}
