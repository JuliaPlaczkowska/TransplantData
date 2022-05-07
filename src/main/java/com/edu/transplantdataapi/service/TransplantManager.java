package com.edu.transplantdataapi.service;

import com.edu.transplantdataapi.dto.prediction.TransplantToPredictDto;
import com.edu.transplantdataapi.repository.TransplantRepo;
import com.edu.transplantdataapi.entity.transplantdata.Transplant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TransplantManager {

    private TransplantRepo transplantRepo;
    private ModelMapper modelMapper = new ModelMapper();

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

    public List<TransplantToPredictDto> getAllAsTransplantToPredictDto(){
        List<TransplantToPredictDto> result = new ArrayList<>();
        findAll()
                .forEach(
                        transplant ->
                                result.add(modelMapper.map(transplant, TransplantToPredictDto.class))
                );
        return result;
    }
}

