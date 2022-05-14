package com.edu.transplantdataapi.service;

import com.edu.transplantdataapi.dto.analysis.HistogramDatasetDto;
import com.edu.transplantdataapi.entity.transplantdata.SurvivalResult;
import com.edu.transplantdataapi.repository.SurvivalResultRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class SurvivalResultManager {

    private SurvivalResultRepo survivalResultRepo;

    public List<SurvivalResult> findAll() {
        return StreamSupport
                .stream(survivalResultRepo.findAll().spliterator(),
                        false)
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


}

