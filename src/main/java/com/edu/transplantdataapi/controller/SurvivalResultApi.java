package com.edu.transplantdataapi.controller;

import com.edu.transplantdataapi.dto.analysis.SurvivalResultsDataGridDto;
import com.edu.transplantdataapi.dto.prediction.SurvivalResultDto;
import com.edu.transplantdataapi.service.transplantdata.SurvivalResultManager;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@CrossOrigin
@AllArgsConstructor
public class SurvivalResultApi {

    private SurvivalResultManager survivalResultManager;

    @GetMapping("api/survivalResult/dataGrid")
    public List<SurvivalResultsDataGridDto> getSurvivalResultsForDataGrid() {
        return survivalResultManager.getSurvivalResultsForDataGrid();
    }

    @GetMapping("api/survivalResult/all")
    public List<SurvivalResultDto> getSurvivalResultsDto() {
//        return new ArrayList<>();
        return survivalResultManager.findAllSurvivalResultDto();
    }

    @PostMapping("api/survivalResult")
    public SurvivalResultDto addSurvivalResult(@RequestBody SurvivalResultDto survivalResult ){
        return  survivalResultManager.save(survivalResult);
    }
}
