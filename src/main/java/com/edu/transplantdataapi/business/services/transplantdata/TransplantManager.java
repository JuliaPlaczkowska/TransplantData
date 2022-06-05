package com.edu.transplantdataapi.business.services.transplantdata;

import com.edu.transplantdataapi.dto.transplantdata.TransplantDto;
import com.edu.transplantdataapi.entities.transplantdata.Transplant;
import com.edu.transplantdataapi.integration.repositories.transplantdata.TransplantRepo;
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
}

