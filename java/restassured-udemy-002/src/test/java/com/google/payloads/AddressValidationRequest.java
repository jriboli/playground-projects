package com.google.payloads;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressValidationRequest {
    private Address address;
    private String previousResponseId;
    private boolean enableUspsCass;
    private LanguageOptions languageOptions;
    private String sessionToken;
}
