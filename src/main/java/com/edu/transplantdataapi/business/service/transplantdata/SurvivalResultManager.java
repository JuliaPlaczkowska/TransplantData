package com.edu.transplantdataapi.business.service.transplantdata;

import com.edu.transplantdataapi.dto.analysis.HistogramDatasetDto;
import com.edu.transplantdataapi.dto.analysis.SurvivalResultsDataGridDto;
import com.edu.transplantdataapi.dto.prediction.SurvivalResultDto;
import com.edu.transplantdataapi.entities.transplantdata.SurvivalResult;
import com.edu.transplantdataapi.entities.enums.ClassFactor;
import com.edu.transplantdataapi.entities.enums.Factor;
import com.edu.transplantdataapi.exceptions.InvalidClassFactorNameException;
import com.edu.transplantdataapi.exceptions.InvalidFactorNameException;
import com.edu.transplantdataapi.integration.repository.SurvivalResultRepo;
import com.edu.transplantdataapi.business.service.user.AuthenticationManager;
import com.edu.transplantdataapi.business.service.analysis.HistogramManager;
import com.edu.transplantdataapi.business.validation.FactorsValidator;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SurvivalResultManager {

    private SurvivalResultRepo survivalResultRepo;
    private HistogramManager histogramManager;
    private AuthenticationManager authenticationManager;
    private ModelMapper modelMapper;

    private List<SurvivalResult> findAll() {
        return survivalResultRepo.findAll();
    }

    public List<SurvivalResultDto> findAllSurvivalResultDto() {
        return survivalResultRepo
                .findAll()
                .stream()
                .map(sr -> modelMapper.map(sr, SurvivalResultDto.class))
                .collect(Collectors.toList());
    }

    public List<SurvivalResultsDataGridDto> getSurvivalResultsForDataGrid() {
        return findAll().stream()
                .map(SurvivalResultsDataGridDto::new)
                .collect(Collectors.toList());
    }

    public HistogramDatasetDto getHistogramData(String factorName,
                                                String classFactorName) throws InvalidFactorNameException, InvalidClassFactorNameException {
        Factor factor = FactorsValidator.validateFactorName(factorName);
        ClassFactor classFactor = FactorsValidator.validateClassFactorName(classFactorName);
        List<SurvivalResult> survivalResultList = findAll();
        return histogramManager.getHistogramData(factor, classFactor, survivalResultList);
    }

    public SurvivalResultDto save(SurvivalResultDto survivalResultDto) {
        SurvivalResult survivalResult = modelMapper.map(survivalResultDto, SurvivalResult.class);
        survivalResult.getTransplant().setUser(authenticationManager.getCurrentUser());
        SurvivalResult saved = survivalResultRepo.save(survivalResult);
        return modelMapper.map(saved, SurvivalResultDto.class);
    }
}

