package com.edu.transplantdataapi.repository;

import com.edu.transplantdataapi.entity.transplantdata.Donor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonorRepo extends JpaRepository<Donor, Long> {
}
