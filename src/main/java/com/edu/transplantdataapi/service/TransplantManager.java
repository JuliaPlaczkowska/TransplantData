package com.edu.transplantdataapi.service;

import com.edu.transplantdataapi.repository.TransplantRepo;
import com.edu.transplantdataapi.entity.transplantdata.Transplant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TransplantManager {

    private TransplantRepo transplantRepo;

    @Autowired
    public TransplantManager(TransplantRepo transplantRepo) {
        this.transplantRepo = transplantRepo;
    }

    public Optional<Transplant> findById(Long id) {
        return transplantRepo.findById(id);
    }

    public Iterable<Transplant> findAll() {
        return transplantRepo.findAll();
    }


    public Iterable<Transplant> findByUsername(String username) {

        Iterable<Transplant> allTransplants = transplantRepo.findAll();

        Iterable<Transplant> withoutResultByUser =
                StreamSupport.stream(
                        allTransplants.spliterator(), false)
                        .filter(transplant ->
                                transplant.getUser().getUsername().equals(username)
                        )
                .collect(Collectors.toList());

        return transplantRepo.findAll();
    }

    public Transplant save(Transplant transplant) {
        return transplantRepo.save(transplant);
    }

}

