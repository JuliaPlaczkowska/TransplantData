package com.edu.transplantdataapi.integration.repository.transplantdata;

import com.edu.transplantdataapi.entities.transplantdata.Transplant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransplantRepo extends JpaRepository<Transplant, Long> {
}
