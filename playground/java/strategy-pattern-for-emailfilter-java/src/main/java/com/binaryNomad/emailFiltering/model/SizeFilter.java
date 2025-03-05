package com.binaryNomad.emailFiltering.model;

import java.util.List;

public class SizeFilter implements FilterStrategy{

    private final Integer MAX_EMAIL_LENGTH = 100;
    @Override
    public List<EmailData> applyFilter(List<EmailData> emails) {

        // Filter out any email over 25 characters
        return emails.stream()
                .filter(e -> e.getBody().length() < MAX_EMAIL_LENGTH)
                .toList();
    }
}
