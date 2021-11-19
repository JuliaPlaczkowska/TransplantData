package com.edu.transplantdataapi.repository;

import com.edu.transplantdataapi.service.Transplant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransplantRepo extends CrudRepository<Transplant, Long> {
}
