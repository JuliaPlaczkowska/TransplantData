package com.edu.transplantdataapi.integration.repositories.prediction;

import com.edu.transplantdataapi.entities.prediction.Prediction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PredictionRepo extends JpaRepository<Prediction, Long> {
}