package com.edu.transplantdataapi.service;

import com.edu.transplantdataapi.dto.analysis.HistogramDatasetDto;
import com.edu.transplantdataapi.dto.analysis.SurvivalResultsDataGridDto;
import com.edu.transplantdataapi.dto.prediction.SurvivalResultDto;
import com.edu.transplantdataapi.entity.transplantdata.SurvivalResult;
import com.edu.transplantdataapi.repository.SurvivalResultRepo;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class SurvivalResultManager {

    private SurvivalResultRepo survivalResultRepo;
    private ModelMapper modelMapper;

    public List<SurvivalResult> findAll() {
        return StreamSupport
                .stream(survivalResultRepo.findAll().spliterator(),
                        false)
                .collect(Collectors.toList());
    }

    public List<SurvivalResultDto> findAllSurvivalResultDto() {
        List<SurvivalResultDto> survivalResultDtos = survivalResultRepo
                .findAll()
                .stream()
                .map(sr -> modelMapper.map(sr, SurvivalResultDto.class))
                .collect(Collectors.toList());
        return survivalResultDtos;
    }

    public List<SurvivalResultsDataGridDto> getSurvivalResultsForDataGrid() {
        return findAll().stream()
                .map(SurvivalResultsDataGridDto::new)
                .collect(Collectors.toList());
    }

    public HistogramDatasetDto getHistogramData(String factor, String classFactor) {
        List<SurvivalResult> survivalResultList = findAll();
        return new HistogramDatasetDto(
                factor,
                classFactor,
                survivalResultList
        );
    }

    public SurvivalResultDto save(SurvivalResultDto survivalResultDto) {
        SurvivalResult survivalResult = modelMapper.map(survivalResultDto, SurvivalResult.class);
        SurvivalResult saved = survivalResultRepo.save(survivalResult);
        return modelMapper.map(saved, SurvivalResultDto.class);
    }
}

