package com.edu.transplantdataapi.controller;

import com.edu.transplantdataapi.datatransferobject.analysis.HistogramDatasetDto;
import com.edu.transplantdataapi.datatransferobject.prediction.PredictionResultDto;
import com.edu.transplantdataapi.datatransferobject.prediction.PredictionTreeDto;
import com.edu.transplantdataapi.datatransferobject.prediction.TransplantDto;
import com.edu.transplantdataapi.datatransferobject.prediction.TransplantPredictionDto;
import com.edu.transplantdataapi.entity.SurvivalResult;
import com.edu.transplantdataapi.entity.prediction.PredictionTree;
import com.edu.transplantdataapi.service.SurvivalResultManager;
import com.edu.transplantdataapi.service.prediction.PredictionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/prediction")
@CrossOrigin
public class PredictionApi {

    private PredictionManager predictions;
    private SurvivalResultManager survivalResults;

    @Autowired
    public PredictionApi(PredictionManager predictions, SurvivalResultManager survivalResults) {
        this.predictions = predictions;
        this.survivalResults = survivalResults;
    }

    @GetMapping("/tree")
    public PredictionTreeDto getPredictionTree(
            //@RequestParam String classFactor
    ) throws Exception {

        PredictionTree predictionTree = new PredictionTree();

        PredictionTreeDto predictionTreeDto = new PredictionTreeDto();
        predictionTreeDto.setTree(predictionTree.getTree().toString());

        return predictionTreeDto;
    }

    @GetMapping("/predict")
    public PredictionResultDto predict(
            @RequestParam TransplantPredictionDto transplantPredictionDto
    ) throws Exception {

        PredictionTree predictionTree = new PredictionTree();

        PredictionResultDto predictionResultDto = new PredictionResultDto();
        predictionResultDto.setClassifiedAs(
                predictionTree.predict(transplantPredictionDto)
        );

        return predictionResultDto;
    }
}
