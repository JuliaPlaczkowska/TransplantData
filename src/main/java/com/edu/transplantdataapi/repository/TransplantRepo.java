package com.edu.transplantdataapi.repository;

import com.edu.transplantdataapi.entity.transplantdata.Transplant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransplantRepo extends JpaRepository<Transplant, Long> {
}
