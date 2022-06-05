package com.edu.transplantdataapi.integration.repositories.transplantdata;

import com.edu.transplantdataapi.entities.transplantdata.SurvivalResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurvivalResultRepo extends JpaRepository<SurvivalResult, Long> {
}
