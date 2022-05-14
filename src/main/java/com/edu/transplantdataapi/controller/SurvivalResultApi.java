package com.edu.transplantdataapi.controller;

import com.edu.transplantdataapi.dto.SurvivalResultsDataGridDto;
import com.edu.transplantdataapi.service.SurvivalResultManager;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping
@CrossOrigin
@AllArgsConstructor
public class SurvivalResultApi {

    private SurvivalResultManager survivalResultManager;

    @GetMapping("api/survivalResult/dataGrid")
    public List<SurvivalResultsDataGridDto> getSurvivalResultsForDataGrid() {

        return survivalResultManager.findAll().stream()
                .map(SurvivalResultsDataGridDto::new)
                .collect(Collectors.toList());
    }


//    @PostMapping("api/survivalResult")
//    public SurvivalResult addSurvivalResult(@RequestBody SurvivalResult survivalResult ){
//        return  survivalResultManager.save(survivalResult);
//    }
}
