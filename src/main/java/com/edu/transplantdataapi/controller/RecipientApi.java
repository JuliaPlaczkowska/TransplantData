package com.edu.transplantdataapi.controller;

import com.edu.transplantdataapi.dto.RecipientDto;
import com.edu.transplantdataapi.entity.transplantdata.Recipient;
import com.edu.transplantdataapi.service.RecipientManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping
@CrossOrigin
public class RecipientApi {

    private RecipientManager recipients;

    @Autowired
    public RecipientApi(RecipientManager recipientManager) {
        this.recipients = recipientManager;
    }

    @GetMapping("api/recipient/all")
    public Iterable<RecipientDto> getAllRecipients() {

        Iterable<Recipient> iterableRecipient = recipients.findAll();
        return StreamSupport
                .stream(iterableRecipient.spliterator(), true)
                .map(this::convertToDto)::iterator;
    }

    @GetMapping("api/recipient")
    public Optional<RecipientDto> getRecipientById(@RequestParam Long index) {

        Optional<Recipient> recipient1 = recipients.findById(index);

        RecipientDto recipientDto = null;
        if (recipient1.isPresent())
            recipientDto = convertToDto(recipient1.get());

        return Optional.ofNullable(recipientDto);
    }

    @GetMapping("api/recipient/isAvailable")
    public boolean isIdAvailable(@RequestParam Long index) {
        Optional<Recipient> recipient1 = recipients.findById(index);

        return recipient1.isEmpty();
    }

    @PostMapping("api/recipient")
    public RecipientDto addRecipient(@RequestBody RecipientDto recipientDto) {

        return convertToDto(recipients.save(convertToEntity(recipientDto)));
    }

    private RecipientDto convertToDto(Recipient recipient) {
        return new RecipientDto(
                recipient.getPatient(),
                recipient.getBloodRh(),
                recipient.getBodyMass(),
                recipient.getDisease(),
                recipient.getDiseaseGroup(),
                recipient.getRiskGroup());
    }

    private Recipient convertToEntity(RecipientDto recipientDto) {
        return new Recipient(
                recipientDto.getPatient(),
                recipientDto.getBloodRh(),
                recipientDto.getBodyMass(),
                recipientDto.getDisease(),
                recipientDto.getDiseaseGroup(),
                recipientDto.getRiskGroup());
    }
}
