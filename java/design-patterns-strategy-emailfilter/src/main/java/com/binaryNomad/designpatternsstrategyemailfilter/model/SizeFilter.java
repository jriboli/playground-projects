package com.binaryNomad.designpatternsstrategyemailfilter.model;

import java.util.List;

public class SizeFilter implements FilterStrategy{
    @Override
    public List<EmailData> applyFilter(List<EmailData> emails) {

        // Filter out any email over 25 characters
        return emails.stream()
                .filter(e -> e.getBody().length() < 100)
                .toList();
    }
}
