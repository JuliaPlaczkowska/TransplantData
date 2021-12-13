package com.edu.transplantdataapi.controller;

import com.edu.transplantdataapi.service.SurvivalResult;
import com.edu.transplantdataapi.service.SurvivalResultManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping
public class SurvivalResultApi {

    private SurvivalResultManager survivalResults;

    @Autowired
    public SurvivalResultApi(SurvivalResultManager survivalResultManager) {
        this.survivalResults = survivalResultManager;
    }

    @GetMapping("api/survivalResult/all")
    public Iterable<SurvivalResult> getAllSurvivalResults() {
        return survivalResults.findAll();
    }

    @GetMapping("api/survivalResult")
    public Optional<SurvivalResult> getSurvivalResultById(@RequestParam Long index) {
        return survivalResults.findById(index);
    }

    @PostMapping("api/survivalResult")
    public SurvivalResult addSurvivalResult(@RequestBody SurvivalResult survivalResult ){
        return  survivalResults.save(survivalResult);
    }
}
