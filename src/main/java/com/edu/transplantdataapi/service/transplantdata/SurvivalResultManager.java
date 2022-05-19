package com.edu.transplantdataapi.service.transplantdata;

import com.edu.transplantdataapi.dto.analysis.HistogramDatasetDto;
import com.edu.transplantdataapi.dto.analysis.SurvivalResultsDataGridDto;
import com.edu.transplantdataapi.dto.prediction.SurvivalResultDto;
import com.edu.transplantdataapi.entity.transplantdata.SurvivalResult;
import com.edu.transplantdataapi.enums.ClassFactor;
import com.edu.transplantdataapi.enums.Factor;
import com.edu.transplantdataapi.exceptions.InvalidClassFactorNameException;
import com.edu.transplantdataapi.exceptions.InvalidFactorNameException;
import com.edu.transplantdataapi.repository.SurvivalResultRepo;
import com.edu.transplantdataapi.service.analysis.HistogramManager;
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
        Factor factor = validateFactorName(factorName);
        ClassFactor classFactor = validateClassFactorName(classFactorName);
        List<SurvivalResult> survivalResultList = findAll();
        return histogramManager.getHistogramData(factor, classFactor, survivalResultList);
    }

    public SurvivalResultDto save(SurvivalResultDto survivalResultDto) {
        SurvivalResult survivalResult = modelMapper.map(survivalResultDto, SurvivalResult.class);
        SurvivalResult saved = survivalResultRepo.save(survivalResult);
        return modelMapper.map(saved, SurvivalResultDto.class);
    }

    private Factor validateFactorName(String factorName) throws InvalidFactorNameException {
        try{
            return Factor.valueOf(factorName);
        }
        catch (IllegalArgumentException e){
            throw new InvalidFactorNameException(factorName);
        }
    }

    private ClassFactor validateClassFactorName(String classFactorName) throws InvalidClassFactorNameException {
        try{
            return ClassFactor.valueOf(classFactorName);
        }
        catch (IllegalArgumentException e){
            throw new InvalidClassFactorNameException(classFactorName);
        }
    }
}
