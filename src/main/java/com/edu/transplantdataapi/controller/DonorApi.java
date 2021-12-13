package com.edu.transplantdataapi.controller;

import com.edu.transplantdataapi.service.Donor;
import com.edu.transplantdataapi.service.DonorManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping
public class DonorApi {

    private DonorManager donors;

    @Autowired
    public DonorApi(DonorManager donorManager) {
        this.donors = donorManager;
    }

    @GetMapping("api/donor/all")
    public Iterable<Donor> getAllDonors() {
        return donors.findAll();
    }

    @GetMapping("api/donor")
    public Optional<Donor> getDonorById(@RequestParam Long index) {
        return donors.findById(index);
    }

    @PostMapping("api/donor")
    public Donor addDonor(@RequestBody Donor donor ){
        return  donors.save(donor);
    }
}
