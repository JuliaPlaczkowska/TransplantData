package com.edu.transplantdataapi.repository.prediction;

import com.edu.transplantdataapi.entity.prediction.Prediction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PredictionRepo extends CrudRepository<Prediction, Long> {
}