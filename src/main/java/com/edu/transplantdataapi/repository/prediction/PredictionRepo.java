package com.edu.transplantdataapi.repository.prediction;

import com.edu.transplantdataapi.entity.prediction.Prediction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PredictionRepo extends JpaRepository<Prediction, Long> {
}