package com.edu.transplantdataapi.integration.repository.analysis;

import com.edu.transplantdataapi.entities.analysis.ChiSquare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChiSquareRepo extends JpaRepository<ChiSquare, Long> {

}
