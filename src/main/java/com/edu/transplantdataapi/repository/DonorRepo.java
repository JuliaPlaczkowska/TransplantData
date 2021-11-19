package com.edu.transplantdataapi.repository;

import com.edu.transplantdataapi.service.Donor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonorRepo extends CrudRepository<Donor, Long> {
}
