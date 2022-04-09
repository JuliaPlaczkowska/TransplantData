package com.edu.transplantdataapi.service.prediction;

import com.edu.transplantdataapi.repository.prediction.PredictionRepo;
import com.edu.transplantdataapi.entity.prediction.Prediction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PredictionManager {

    private PredictionRepo predictionRepo;

    @Autowired
    public PredictionManager(PredictionRepo predictionRepo) {
        this.predictionRepo = predictionRepo;
    }

    public Optional<Prediction> findById(Long id){
        return predictionRepo.findById(id);
    }

    public Iterable<Prediction> findAll(){
        return predictionRepo.findAll();
    }

    public Prediction save(Prediction prediction){

        return predictionRepo.save(prediction);
    }
}