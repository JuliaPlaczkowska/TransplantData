package com.edu.transplantdataapi.repository;


import com.edu.transplantdataapi.entity.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepo extends CrudRepository<Patient, Long> {
}
