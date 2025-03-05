package com.binaryNomad.emailFiltering.model;

import java.util.List;

public class MachineLearningFilter implements FilterStrategy{
    @Override
    public List<EmailData> applyFilter(List<EmailData> emails) {

        // Remove anything the contains "unsubscribe"
        return emails.stream()
                .filter(e -> !e.getBody().toLowerCase().contains("unsubscribe"))
                .toList();
    }
}
