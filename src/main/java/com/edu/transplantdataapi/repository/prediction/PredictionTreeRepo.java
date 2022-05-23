package com.edu.transplantdataapi.repository.prediction;

import com.edu.transplantdataapi.entity.prediction.PredictionTree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PredictionTreeRepo extends JpaRepository<PredictionTree, Long> {
}
