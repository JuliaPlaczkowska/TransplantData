package com.edu.transplantdataapi.integration.repositories.analysis;

import com.edu.transplantdataapi.entities.analysis.AnalysisResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalysisResultRepo extends JpaRepository<AnalysisResult, Long> {
}
