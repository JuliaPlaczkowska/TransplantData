package com.edu.transplantdataapi.controller;


import com.edu.transplantdataapi.dto.analysis.ChiSquareTestDto;
import com.edu.transplantdataapi.dto.analysis.HistogramDatasetDto;
import com.edu.transplantdataapi.entity.transplantdata.SurvivalResult;
import com.edu.transplantdataapi.service.SurvivalResultManager;
import com.edu.transplantdataapi.service.analysis.ChiSquareManager;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@CrossOrigin
@AllArgsConstructor
public class AnalysisApi {

    private SurvivalResultManager survivalResultsManager;
    private ChiSquareManager chiSquareManager;

    @GetMapping("api/survivalResult/histogram")
    public HistogramDatasetDto getHistogramData(
            @RequestParam String factor,
            @RequestParam String classFactor
    ) {

        return survivalResultsManager.getHistogramData(factor, classFactor);
    }

    @GetMapping("api/survivalResult/chisquaretest")
    public ChiSquareTestDto getChiSquareTestResult(
            @RequestParam String factor,
            @RequestParam String classFactor,
            @RequestParam double significance
    ) {

        List<SurvivalResult> survivalResultList =
                survivalResultsManager.findAll();

        return chiSquareManager.getChiSquareTestResult(
                factor,
                classFactor,
                survivalResultList,
                significance);
    }

}
