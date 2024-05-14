package com.binaryNomad.emailFiltering.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailData {
    private String fromAddress;
    private String toAddress;
    private String subject;
    private String body;

    private boolean hasAttachment;
}
