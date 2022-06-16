package com.edu.transplantdataapi.integration.repositories.transplantdata;

import com.edu.transplantdataapi.entities.transplantdata.patient.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepo extends JpaRepository<Patient, Long> {
    Boolean existsByNumber(long number);
}