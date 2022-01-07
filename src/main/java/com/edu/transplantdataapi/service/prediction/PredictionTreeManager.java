package com.edu.transplantdataapi.service.prediction;

import com.edu.transplantdataapi.repository.prediction.PredictionTreeRepo;
import com.edu.transplantdataapi.entity.prediction.PredictionTree;
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
