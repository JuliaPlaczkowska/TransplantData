package com.edu.transplantdataapi.controller;


import com.edu.transplantdataapi.dto.analysis.ChiSquareTestDto;
import com.edu.transplantdataapi.dto.analysis.HistogramDatasetDto;
import com.edu.transplantdataapi.entity.transplantdata.SurvivalResult;
import com.edu.transplantdataapi.entity.analysis.ChiSquare;
import com.edu.transplantdataapi.service.SurvivalResultManager;
import com.edu.transplantdataapi.service.analysis.ChiSquareManager;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping
@CrossOrigin
@AllArgsConstructor
public class AnalysisApi {

    private SurvivalResultManager survivalResultsManager;
    private ChiSquareManager chiSquareManager;

    @GetMapping("api/survivalResult/histogram")
    public HistogramDatasetDto getSurvivalResultsForDataGrid(
            @RequestParam String factor,
            @RequestParam String classFactor
    ) {

        List<SurvivalResult> survivalResultList = StreamSupport
                .stream(survivalResultsManager.findAll().spliterator(),
                        false)
                .collect(Collectors.toList());

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
                        survivalResultsManager.findAll().spliterator(), false)
                        .collect(Collectors.toList())
        );

        ChiSquare chiSquare = new ChiSquare(
                factor,
                classFactor,
                survivalResultList,
                significance);

        chiSquareManager.generateObservedExpected(chiSquare);
        chiSquareManager.calculatePValue(chiSquare);
        chiSquareManager.calculateReject(chiSquare);

        return new ChiSquareTestDto(
                chiSquare.getPValue(),
                chiSquare.isRejected()
        );
    }

}
