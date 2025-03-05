package com.binaryNomad.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Address {
    private int revision;
    private String regionCode;
    private String languageCode;
    private String postalCode;
    private String sortingCode;
    private String administrativeArea;
    private String locality;
    private String sublocality;
    private List<String> addressLines;
    private List<String> recipients;
    private String organization;
}
