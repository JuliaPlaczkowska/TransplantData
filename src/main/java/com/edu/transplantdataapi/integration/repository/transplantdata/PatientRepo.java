package com.edu.transplantdataapi.integration.repository.transplantdata;


import com.edu.transplantdataapi.entities.transplantdata.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepo extends JpaRepository<Patient, Long> {
}
