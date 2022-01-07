package com.edu.transplantdataapi.repository.analysis;

import com.edu.transplantdataapi.entity.analysis.AnalysisResult;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalysisResultRepo extends CrudRepository<AnalysisResult, Long> {
}
