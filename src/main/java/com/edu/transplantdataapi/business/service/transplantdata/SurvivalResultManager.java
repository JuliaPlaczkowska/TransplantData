package com.edu.transplantdataapi.business.service.transplantdata;

import com.edu.transplantdataapi.dto.analysis.HistogramDatasetDto;
import com.edu.transplantdataapi.dto.analysis.SurvivalResultsDataGridDto;
import com.edu.transplantdataapi.dto.prediction.SurvivalResultDto;
import com.edu.transplantdataapi.entities.transplantdata.SurvivalResult;
import com.edu.transplantdataapi.entities.enums.ClassFactor;
import com.edu.transplantdataapi.entities.enums.Factor;
import com.edu.transplantdataapi.entities.transplantdata.Transplant;
import com.edu.transplantdataapi.entities.transplantdata.patient.Donor;
import com.edu.transplantdataapi.entities.transplantdata.patient.Recipient;
import com.edu.transplantdataapi.exceptions.InvalidClassFactorNameException;
import com.edu.transplantdataapi.exceptions.InvalidFactorNameException;
import com.edu.transplantdataapi.integration.repository.transplantdata.SurvivalResultRepo;
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
                .map(this::mapSurvivalResult)
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
        survivalResult.getTransplant().setAuthor(authenticationManager.getCurrentUser());
        SurvivalResult saved = survivalResultRepo.save(survivalResult);
        return modelMapper.map(saved, SurvivalResultDto.class);
    }

    private SurvivalResultsDataGridDto mapSurvivalResult(SurvivalResult survivalResult) {
        SurvivalResultsDataGridDto dto =
                new ModelMapper().map(survivalResult, SurvivalResultsDataGridDto.class);

        Recipient recipient = survivalResult.getTransplant().getRecipient();
        dto.setRecipientNumber(recipient.getPatient().getNumber());
        dto.setRecipientAge(recipient.getPatient().getAge());
        dto.setRecipientBloodABO(recipient.getPatient().getBloodABO());
        dto.setRecipientPresenceOfCMV(recipient.getPatient().getPresenceOfCMV());
        dto.setBloodRh(recipient.getBloodRh());
        dto.setBodyMass(recipient.getBodyMass());
        dto.setDisease(recipient.getDisease());
        dto.setDiseaseGroup(recipient.getDiseaseGroup());
        dto.setRiskGroup(recipient.getRiskGroup());

        Donor donor = survivalResult.getTransplant().getDonor();
        dto.setDonorNumber(donor.getPatient().getNumber());
        dto.setDonorAge(donor.getPatient().getAge());
        dto.setDonorBloodABO(donor.getPatient().getBloodABO());
        dto.setDonorPresenceOfCMV(donor.getPatient().getPresenceOfCMV());
        dto.setStemCellSource(donor.getStemCellSource());

        Transplant transplant = survivalResult.getTransplant();
        dto.setMatchHLA(transplant.getMatchHLA());
        dto.setMismatchHLA(transplant.isMismatchHLA());
        dto.setAntigen(transplant.getAntigen());
        dto.setAllele(transplant.getAllele());
        dto.setGroup1HLA(transplant.getGroup1HLA());
        dto.setPostRelapse(transplant.isPostRelapse());
        dto.setCD34perKg(transplant.getCD34perKg());
        dto.setCD3perKg(transplant.getCD3perKg());

        return dto;
    }
}

