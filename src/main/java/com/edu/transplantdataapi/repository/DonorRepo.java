package com.edu.transplantdataapi.repository;

import com.edu.transplantdataapi.entity.transplantdata.Donor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonorRepo extends CrudRepository<Donor, Long> {
}
