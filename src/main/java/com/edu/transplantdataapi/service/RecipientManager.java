package com.edu.transplantdataapi.service;

import com.edu.transplantdataapi.repository.RecipientRepo;
import com.edu.transplantdataapi.entity.transplantdata.Recipient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class RecipientManager {

    private RecipientRepo recipientRepo;

    @Autowired
    public RecipientManager(RecipientRepo recipientRepo){
        this.recipientRepo = recipientRepo;
    }

    public Optional<Recipient> findById(Long id){
        return recipientRepo.findById(id);
    }

    public Iterable<Recipient> findAll(){
        return recipientRepo.findAll();
    }

    public Recipient save(Recipient recipient){
        return recipientRepo.save(recipient);
    }

}

