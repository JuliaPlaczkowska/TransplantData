package com.edu.transplantdataapi.integration.repository.transplantdata;

import com.edu.transplantdataapi.entities.transplantdata.Recipient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipientRepo extends JpaRepository<Recipient, Long> {
}
