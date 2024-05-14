package com.binaryNomad.emailFiltering.service;

import com.binaryNomad.emailFiltering.model.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class EmailFilterService {

    private FilterStrategy strategy;

    private List<EmailData> emails = new ArrayList<>();

    public EmailFilterService() {
        //this.emails = generateEmails();

        emails.add(new EmailData("sender@example.com", "recipient@example.com", "Meeting invitation", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", true));
        emails.add(new EmailData("sender@example.com", "recipient@example.com", "Update", "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", false));
        emails.add(new EmailData("sender@example.com", "recipient@example.com", "Newsletter", "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat - unsubscribe.", true));
        emails.add(new EmailData("sender@example.com", "recipient@example.com", "Important announcement", "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.", false));

    }

    public void setStrategy(String strategyName) {
        switch(strategyName){
            case "size":
                strategy = new SizeFilter();
                break;
            case "rulebased":
                strategy = new RuleBasedFilter();
                break;
            case "ml":
                strategy = new MachineLearningFilter();
                break;
            default:
                throw new IllegalArgumentException("No such Strategy exists");
        }
    }

    public EmailDataResponse allEmails() {
        return new EmailDataResponse(emails);
    }

    public EmailDataResponse filterEmails() {
        List<EmailData> filteredEmails = strategy.applyFilter(emails);

        return new EmailDataResponse(filteredEmails);
    }

    public List<EmailData> generateEmails() {
        List<EmailData> emails = new ArrayList<>();
        Random random = new Random();
        String[] subjects = { "Meeting invitation", "Update", "Newsletter", "Important announcement" };
        String[] bodies = { "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
                "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur." };

        for (int i = 0; i < 25; i++) {
            String from = "sender@example.com";
            String to = "recipient@example.com";
            String subject = subjects[random.nextInt(subjects.length)];
            String body = bodies[random.nextInt(bodies.length)];
            boolean hasAttachment = random.nextBoolean();

            // Add "unsubscribe" to some email bodies
            if (i % 5 == 0) {
                body += " unsubscribe";
            }

            // Ensure body length > 25 for some emails
            if (i % 7 == 0) {
                while (body.length() <= 25) {
                    body += " more text";
                }
            }

            // Add "meeting" to some email subjects
            if (i % 3 == 0) {
                subject += " meeting";
            }

            emails.add(new EmailData(from, to, subject, body, hasAttachment));
        }

        return emails;
    }


}
