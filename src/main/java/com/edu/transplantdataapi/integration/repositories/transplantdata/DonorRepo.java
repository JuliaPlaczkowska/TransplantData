package com.edu.transplantdataapi.integration.repositories.transplantdata;

import com.edu.transplantdataapi.entities.transplantdata.patient.Donor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonorRepo extends JpaRepository<Donor, Long> {
}
