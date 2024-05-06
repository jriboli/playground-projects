package com.binaryNomad.caching.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comic {
    private int id;
    private String hero;
    private String villian;
}
