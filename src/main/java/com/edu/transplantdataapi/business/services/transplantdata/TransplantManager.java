package com.edu.transplantdataapi.business.services.transplantdata;

import com.edu.transplantdataapi.business.services.user.AuthenticationManager;
import com.edu.transplantdataapi.business.validation.IdentificationNumberValidator;
import com.edu.transplantdataapi.dto.transplantdata.TransplantDto;
import com.edu.transplantdataapi.entities.transplantdata.Transplant;
import com.edu.transplantdataapi.integration.repositories.transplantdata.PatientRepo;
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
    private PatientRepo patientRepo;
    private AuthenticationManager authenticationManager;
    private ModelMapper mapper;

    public TransplantDto save(TransplantDto transplantDto) {
        validateIdentificationNumbers(transplantDto);
        Transplant transplantToSave = mapper.map(transplantDto, Transplant.class);
        transplantToSave.setAuthor(authenticationManager.getCurrentUser());
        Transplant transplant = transplantRepo.save(transplantToSave);
        return mapper.map(transplant, TransplantDto.class);
    }

    private Iterable<Transplant> findAll() {
        return transplantRepo.findAll();
    }

    private void validateIdentificationNumbers(TransplantDto transplantDto) {
        IdentificationNumberValidator identificationNumberValidator =
                new IdentificationNumberValidator(
                        patientRepo,
                        transplantRepo
                );
        Long transplantNumber = transplantDto.getNumber();
        Long donorNumber = transplantDto
                .getDonor()
                .getPatient()
                .getNumber();
        Long recipientNumber = transplantDto
                .getRecipient()
                .getPatient()
                .getNumber();

        identificationNumberValidator.validateTransplantIdentificationNumber(transplantNumber);
        identificationNumberValidator.validatePatientIdentificationNumber(donorNumber);
        identificationNumberValidator.validatePatientIdentificationNumber(recipientNumber);
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

