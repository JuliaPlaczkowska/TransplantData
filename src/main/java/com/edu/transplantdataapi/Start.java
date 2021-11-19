package com.edu.transplantdataapi;

import com.edu.transplantdataapi.repository.DonorRepo;
import com.edu.transplantdataapi.service.Donor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Start {

    private DonorRepo donorRepo;

    @Autowired
    public Start(DonorRepo donorRepo){
        this.donorRepo = donorRepo;
    }

    public void runExample(){
    }
}
