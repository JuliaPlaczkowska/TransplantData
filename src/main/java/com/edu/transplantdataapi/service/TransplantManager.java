package com.edu.transplantdataapi.service;

import com.edu.transplantdataapi.repository.TransplantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class TransplantManager {

    private TransplantRepo transplantRepo;

    @Autowired
    public TransplantManager(TransplantRepo transplantRepo){
        this.transplantRepo = transplantRepo;
    }

    public Optional<Transplant> findById(Long id){
        return transplantRepo.findById(id);
    }

    public Iterable<Transplant> findAll(){
        return transplantRepo.findAll();
    }

    public Transplant save(Transplant transplant){
        return transplantRepo.save(transplant);
    }

}

