package com.edu.transplantdataapi.service;

import com.edu.transplantdataapi.repository.SurvivalResultRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class SurvivalResultManager {

    private SurvivalResultRepo survivalResultRepo;

    @Autowired
    public SurvivalResultManager(SurvivalResultRepo survivalResultRepo){
        this.survivalResultRepo = survivalResultRepo;
    }

    public Optional<SurvivalResult> findById(Long id){
        return survivalResultRepo.findById(id);
    }

    public Iterable<SurvivalResult> findAll(){
        return survivalResultRepo.findAll();
    }

    public SurvivalResult save(SurvivalResult survivalResult){
        return survivalResultRepo.save(survivalResult);
    }

}

