package com.edu.transplantdataapi.integration.repositories.prediction;

import com.edu.transplantdataapi.entities.prediction.PredictionTree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PredictionTreeRepo extends JpaRepository<PredictionTree, Long> {
}
