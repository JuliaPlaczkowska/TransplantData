package com.edu.transplantdataapi.repository.prediction;

import com.edu.transplantdataapi.entity.prediction.PredictionTree;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PredictionTreeRepo extends CrudRepository<PredictionTree, Long> {
}
