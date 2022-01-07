package com.edu.transplantdataapi.service;

import com.edu.transplantdataapi.repository.AccountRepo;
import com.edu.transplantdataapi.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class AccountManager {

    private AccountRepo accountRepo;

    @Autowired
    public AccountManager(AccountRepo accountRepo){
        this.accountRepo = accountRepo;
    }

    public Optional<Account> findById(Long id){
        return accountRepo.findById(id);
    }

    public Iterable<Account> findAll(){
        return accountRepo.findAll();
    }

    public Account save(Account account){
        return accountRepo.save(account);
    }

    public void deleteById(Long id){ accountRepo.deleteById(id); }
}
