package com.edu.transplantdataapi.controller;

import com.edu.transplantdataapi.entity.Patient;
import com.edu.transplantdataapi.service.PatientManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping
@CrossOrigin
public class PatientApi {

    private PatientManager patients;

    @Autowired
    public PatientApi(PatientManager patientManager) {
        this.patients = patientManager;
    }

    @GetMapping("api/patient/all")
    public Iterable<Patient> getAllPatients() {
        return patients.findAll();
    }

    @GetMapping("api/patient")
    public Optional<Patient> getPatientById(@RequestParam Long index) {
        return patients.findById(index);
    }

    @PostMapping("api/patient")
    public Patient addPatient(@RequestBody Patient patient ){
        return  patients.save(patient);
    }
}
