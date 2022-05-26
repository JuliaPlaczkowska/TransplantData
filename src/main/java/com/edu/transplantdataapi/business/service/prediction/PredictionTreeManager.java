package com.edu.transplantdataapi.business.service.prediction;

import com.edu.transplantdataapi.integration.repository.prediction.PredictionTreeRepo;
import com.edu.transplantdataapi.entities.prediction.PredictionTree;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class PredictionTreeManager {

    private PredictionTreeRepo predictionTreeRepo;

    @Autowired
    public PredictionTreeManager(PredictionTreeRepo predictionTreeRepo) {
        this.predictionTreeRepo = predictionTreeRepo;
    }

    public Optional<PredictionTree> findById(Long id){
        return predictionTreeRepo.findById(id);
    }

    public Iterable<PredictionTree> findAll(){
        return predictionTreeRepo.findAll();
    }

    public PredictionTree save(PredictionTree predictionTree){
        return predictionTreeRepo.save(predictionTree);
    }

}
