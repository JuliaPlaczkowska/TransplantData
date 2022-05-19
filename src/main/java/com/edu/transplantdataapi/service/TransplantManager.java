package com.edu.transplantdataapi.service;

import com.edu.transplantdataapi.dto.prediction.TransplantDto;
import com.edu.transplantdataapi.dto.prediction.TransplantToPredictDto;
import com.edu.transplantdataapi.entity.transplantdata.Transplant;
import com.edu.transplantdataapi.repository.TransplantRepo;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TransplantManager {

    private TransplantRepo transplantRepo;
    private ModelMapper mapper;

    public TransplantDto save(TransplantDto transplantDto) {
        //TODO validation
        Transplant transplant = transplantRepo
                .save(mapper.map(transplantDto, Transplant.class));
        return mapper.map(transplant, TransplantDto.class);
    }

    private Iterable<Transplant> findAll() {
        return transplantRepo.findAll();
    }

    public List<TransplantDto> getAllTransplantDto() {
        List<TransplantDto> result = new ArrayList<>();
        findAll()
                .forEach(
                        transplant ->
                                result.add(
                                        mapper
                                                .map(transplant, TransplantDto.class)
                                )
                );
        return result;
    }

    public List<TransplantToPredictDto> getAllAsTransplantToPredictDto() {
        List<TransplantToPredictDto> result = new ArrayList<>();
        findAll()
                .forEach(
                        transplant ->
                                result.add(
                                        mapper
                                                .map(transplant, TransplantToPredictDto.class)
                                )
                );
        return result;
    }
}

