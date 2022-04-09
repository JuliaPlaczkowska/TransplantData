package com.edu.transplantdataapi.repository;

import com.edu.transplantdataapi.entity.user.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends CrudRepository<Account, Long> {
}
