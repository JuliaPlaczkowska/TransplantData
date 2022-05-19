package com.edu.transplantdataapi.controller;

import com.edu.transplantdataapi.dto.prediction.TransplantDto;
import com.edu.transplantdataapi.service.transplantdata.TransplantManager;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping
@CrossOrigin
public class TransplantApi {

    private TransplantManager transplants;

    @GetMapping("api/transplant/all")
    public Iterable<TransplantDto> getAllTransplants() {
        return transplants.getAllTransplantDto();
    }

    @PostMapping("api/transplant")
    public TransplantDto addTransplant(@RequestBody TransplantDto transplantDto) {
        return transplants.save(transplantDto);
    }
}
