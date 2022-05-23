package com.edu.transplantdataapi.repository.analysis;

import com.edu.transplantdataapi.entity.analysis.ChiSquare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChiSquareRepo extends JpaRepository<ChiSquare, Long> {

}
