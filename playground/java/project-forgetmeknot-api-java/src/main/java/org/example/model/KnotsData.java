package org.example.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
public class KnotsData {

    private Long knotId;
    private String knotName;
    private String knotDescription;
    private LocalTime knotCreated;
    private Integer knotRemindInDays;
}
