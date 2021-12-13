package com.edu.transplantdataapi.controller;

import com.edu.transplantdataapi.service.Recipient;
import com.edu.transplantdataapi.service.RecipientManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping
public class RecipientApi {

    private RecipientManager recipients;

    @Autowired
    public RecipientApi(RecipientManager recipientManager) {
        this.recipients = recipientManager;
    }

    @GetMapping("api/Recipient/all")
    public Iterable<Recipient> getAllRecipients() {
        return recipients.findAll();
    }

    @GetMapping("api/Recipient")
    public Optional<Recipient> getRecipientById(@RequestParam Long index) {
        return recipients.findById(index);
    }

    @PostMapping("api/recipient")
    public Recipient addRecipient(@RequestBody Recipient recipient ){
        return  recipients.save(recipient);
    }
}
