package com.edu.transplantdataapi.business.service.transplantdata;

import com.edu.transplantdataapi.integration.repository.PatientRepo;
import com.edu.transplantdataapi.entities.transplantdata.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class PatientManager {

    private PatientRepo patientRepo;

    @Autowired
    public PatientManager(PatientRepo patientRepo){
        this.patientRepo = patientRepo;
    }

    public Optional<Patient> findById(Long id){
        return patientRepo.findById(id);
    }

    public Iterable<Patient> findAll(){
        return patientRepo.findAll();
    }

    public Patient save(Patient patient){
        return patientRepo.save(patient);
    }

}

