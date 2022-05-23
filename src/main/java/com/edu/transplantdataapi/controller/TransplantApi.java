package com.edu.transplantdataapi.controller;

import com.edu.transplantdataapi.dto.prediction.TransplantDto;
import com.edu.transplantdataapi.service.transplantdata.TransplantManager;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping
@CrossOrigin
public class TransplantApi {

    private TransplantManager transplants;

    @GetMapping("api/transplant/all")
    public ResponseEntity<?> getAllTransplants() {
        return ResponseEntity.ok(transplants.getAllTransplantDto());
    }

    @PostMapping("api/transplant")
    public ResponseEntity<?> addTransplant(@RequestBody TransplantDto transplantDto) {
        return ResponseEntity.ok(transplants.save(transplantDto));
    }
}
