package com.binaryNomad.designpatternsstrategyemailfilter.model;

import java.util.List;
import java.util.logging.Filter;

public class RuleBasedFilter implements FilterStrategy {
    @Override
    public List<EmailData> applyFilter(List<EmailData> emails) {

        // Filter out anything with Meeting in the subject
        return emails.stream()
                .filter(e -> !e.getSubject().toLowerCase().contains("meeting"))
                .toList();
    }
}
