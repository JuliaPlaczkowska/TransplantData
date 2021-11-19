package com.edu.transplantdataapi.repository;

import com.edu.transplantdataapi.service.Recipient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipientRepo extends CrudRepository<Recipient, Long> {
}
