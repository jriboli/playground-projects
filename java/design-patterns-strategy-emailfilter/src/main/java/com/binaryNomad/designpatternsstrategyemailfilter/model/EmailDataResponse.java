package com.binaryNomad.designpatternsstrategyemailfilter.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailDataResponse {
    List<EmailData> emails;
}
