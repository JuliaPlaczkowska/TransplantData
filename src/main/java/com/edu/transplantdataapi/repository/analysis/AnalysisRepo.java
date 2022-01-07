package com.edu.transplantdataapi.repository.analysis;

import com.edu.transplantdataapi.entity.analysis.Analysis;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalysisRepo extends CrudRepository<Analysis, Long> {
}
