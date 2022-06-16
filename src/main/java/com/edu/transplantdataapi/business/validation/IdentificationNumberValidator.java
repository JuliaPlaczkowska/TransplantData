package com.edu.transplantdataapi.business.validation;

import com.edu.transplantdataapi.exceptions.InvalidIdentificationNumberException;
import com.edu.transplantdataapi.integration.repositories.transplantdata.PatientRepo;
import com.edu.transplantdataapi.integration.repositories.transplantdata.TransplantRepo;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class IdentificationNumberValidator {

    private PatientRepo patientRepo;
    private TransplantRepo transplantRepo;
    private static final String NULL_MESSAGE = "Identification number can not be null";
    private static final String ALREADY_IN_USE_MESSAGE = "Number is already in use: ";


    public void validatePatientIdentificationNumber(Long number) {
        if (number == null)
            throw new InvalidIdentificationNumberException(NULL_MESSAGE);
        else if (patientRepo.existsByNumber(number))
            throw new InvalidIdentificationNumberException(ALREADY_IN_USE_MESSAGE + number);
    }

    public void validateTransplantIdentificationNumber(Long number) {
        if (number == null)
            throw new InvalidIdentificationNumberException(NULL_MESSAGE);
        else if (transplantRepo.existsByNumber(number))
            throw new InvalidIdentificationNumberException(ALREADY_IN_USE_MESSAGE + number);
    }
}
