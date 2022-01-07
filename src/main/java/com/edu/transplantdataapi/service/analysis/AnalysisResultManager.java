package com.edu.transplantdataapi.service.analysis;

import com.edu.transplantdataapi.repository.analysis.AnalysisResultRepo;
import com.edu.transplantdataapi.entity.analysis.AnalysisResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnalysisResultManager {

    private AnalysisResultRepo analysisResultRepo;

    @Autowired
    public AnalysisResultManager(AnalysisResultRepo analysisResultRepo) {
        this.analysisResultRepo = analysisResultRepo;
    }

    public Optional<AnalysisResult> findById(Long id) {
        return analysisResultRepo.findById(id);
    }

    public Iterable<AnalysisResult> findAll() {
        return analysisResultRepo.findAll();
    }

    public AnalysisResult save(AnalysisResult analysisResult) {
        return analysisResultRepo.save(analysisResult);
    }

}