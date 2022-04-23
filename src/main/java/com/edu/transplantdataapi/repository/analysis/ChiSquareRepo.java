package com.edu.transplantdataapi.repository.analysis;

import com.edu.transplantdataapi.entity.analysis.ChiSquare;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChiSquareRepo extends CrudRepository<ChiSquare, Long> {

}
