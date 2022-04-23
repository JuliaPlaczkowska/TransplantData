package com.edu.transplantdataapi.service.analysis;

import com.edu.transplantdataapi.repository.analysis.AnalysisRepo;
import com.edu.transplantdataapi.entity.analysis.Analysis;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AnalysisManager {

    private AnalysisRepo analysisRepo;

    public Optional<Analysis> findById(Long id) {
        return analysisRepo.findById(id);
    }

    public Iterable<Analysis> findAll() {
        return analysisRepo.findAll();
    }

    public Analysis save(Analysis analysis) {
        return analysisRepo.save(analysis);
    }

}
