package com.binaryNomad.emailFiltering.controller;

import com.binaryNomad.emailFiltering.model.EmailDataResponse;
import com.binaryNomad.emailFiltering.service.EmailFilterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/email")
public class EmailFilterController {

    private EmailFilterService service;

    public EmailFilterController(EmailFilterService service) {
        this.service = service;

    }

    @GetMapping()
    public ResponseEntity<EmailDataResponse> getAllEmails() {
        return ResponseEntity.ok(service.allEmails());
    }

    @GetMapping("/filter")
    public ResponseEntity<EmailDataResponse> getEmailsWithFilter(
            @RequestParam (required = false) Optional<String> strategy) {

        service.setStrategy(strategy.get());
        return ResponseEntity.ok(service.filterEmails());
    }
}