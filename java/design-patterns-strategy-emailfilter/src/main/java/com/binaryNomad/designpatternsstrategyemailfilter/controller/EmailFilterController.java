package com.binaryNomad.designpatternsstrategyemailfilter.controller;

import com.binaryNomad.designpatternsstrategyemailfilter.model.EmailData;
import com.binaryNomad.designpatternsstrategyemailfilter.model.EmailDataResponse;
import com.binaryNomad.designpatternsstrategyemailfilter.service.EmailFilterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

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