package com.helldivers.entity.stratagems;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Flag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flagId;
    private String name;
}
