package com.edu.transplantdataapi.repository;

import com.edu.transplantdataapi.entity.transplantdata.SurvivalResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurvivalResultRepo extends JpaRepository<SurvivalResult, Long> {
}
