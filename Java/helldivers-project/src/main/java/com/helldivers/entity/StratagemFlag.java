package com.helldivers.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class StratagemFlag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flagId;
    private String name;
}
