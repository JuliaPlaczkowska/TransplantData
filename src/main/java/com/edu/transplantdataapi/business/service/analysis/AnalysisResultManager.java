package com.edu.transplantdataapi.business.service.analysis;

import com.edu.transplantdataapi.dto.analysis.AnalysisResultDto;
import com.edu.transplantdataapi.integration.repository.analysis.AnalysisResultRepo;
import com.edu.transplantdataapi.entities.analysis.AnalysisResult;
import com.edu.transplantdataapi.business.service.user.AuthenticationManager;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class AnalysisResultManager {

    private AnalysisResultRepo analysisResultRepo;
    private AuthenticationManager authenticationManager;
    private ModelMapper mapper;

    public List<AnalysisResultDto> findAll() {
        return analysisResultRepo
                .findAll()
                .stream()
                .map(result ->
                        mapper.map(result, AnalysisResultDto.class)
                ).collect(Collectors.toList());
    }

    public AnalysisResultDto save(AnalysisResultDto dto) {
        AnalysisResult entityToSave = mapper.map(dto, AnalysisResult.class);
        entityToSave.setAuthor(authenticationManager.getCurrentUser());
        AnalysisResult saved = analysisResultRepo.save(entityToSave);
        return mapper.map(saved, AnalysisResultDto.class);
    }

}