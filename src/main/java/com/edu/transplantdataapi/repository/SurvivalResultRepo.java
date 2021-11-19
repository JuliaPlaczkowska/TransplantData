package com.edu.transplantdataapi.repository;

import com.edu.transplantdataapi.service.SurvivalResult;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurvivalResultRepo extends CrudRepository<SurvivalResult, Long> {
}
