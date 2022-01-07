package com.edu.transplantdataapi.controller;


import com.edu.transplantdataapi.datatransferobject.analysis.ChiSquareTestDto;
import com.edu.transplantdataapi.datatransferobject.analysis.HistogramDatasetDto;
import com.edu.transplantdataapi.entity.SurvivalResult;
import com.edu.transplantdataapi.entity.analysis.ChiSquare;
import com.edu.transplantdataapi.service.SurvivalResultManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping
@CrossOrigin
public class AnalysisApi {

    private SurvivalResultManager survivalResults;

    @Autowired
    public AnalysisApi(SurvivalResultManager survivalResultManager) {
        this.survivalResults = survivalResultManager;
    }

    @GetMapping("api/survivalResult/histogram")
    public HistogramDatasetDto getSurvivalResultsForDataGrid(
            @RequestParam String factor, String classFactor
    ) {

        List<SurvivalResult> survivalResultList = new ArrayList<>(
                StreamSupport.stream(
                        survivalResults.findAll().spliterator(), false)
                        .collect(Collectors.toList())
        );

        return new HistogramDatasetDto(
                factor,
                classFactor,
                survivalResultList
        );
    }

    @GetMapping("api/survivalResult/chisquaretest")
    public ChiSquareTestDto getChiSquareTestResult(
            @RequestParam String factor,
            @RequestParam String classFactor,
            @RequestParam double significance
    ) {

        List<SurvivalResult> survivalResultList = new ArrayList<>(
                StreamSupport.stream(
                        survivalResults.findAll().spliterator(), false)
                        .collect(Collectors.toList())
        );

        ChiSquare chiSquare = new ChiSquare(
                factor,
                classFactor,
                survivalResultList,
                significance);

        return new ChiSquareTestDto(
                chiSquare.getPValue(),
                chiSquare.isRejected()
        );
    }

}
