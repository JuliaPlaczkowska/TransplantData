package com.edu.transplantdataapi.integration.repository;

import com.edu.transplantdataapi.entities.user.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {
}
