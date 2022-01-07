package com.edu.transplantdataapi.controller;

import com.edu.transplantdataapi.entity.Transplant;
import com.edu.transplantdataapi.service.TransplantManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping
@CrossOrigin
public class TransplantApi {

    private TransplantManager transplants;

    @Autowired
    public TransplantApi(TransplantManager transplantManager) {
        this.transplants = transplantManager;
    }

    @GetMapping("api/transplant/all")
    public Iterable<Transplant> getAllTransplants() {
        return transplants.findAll();
    }

    @GetMapping("api/transplant")
    public Optional<Transplant> getTransplantById(@RequestParam Long index) {
        return transplants.findById(index);
    }

    @GetMapping("api/transplant/user")
    public Iterable<Transplant> getTransplantByUsername(
            @RequestParam String username) {
        return transplants.findByUsername(username);
    }


    @PostMapping("api/transplant")
    public Transplant addTransplant(@RequestBody Transplant transplant ){
        return  transplants.save(transplant);
    }
}
