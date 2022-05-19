package com.edu.transplantdataapi.repository.analysis;

import com.edu.transplantdataapi.entity.analysis.AnalysisResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalysisResultRepo extends JpaRepository<AnalysisResult, Long> {
}
