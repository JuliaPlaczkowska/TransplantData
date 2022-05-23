package com.edu.transplantdataapi.repository;

import com.edu.transplantdataapi.entity.transplantdata.Recipient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipientRepo extends JpaRepository<Recipient, Long> {
}
