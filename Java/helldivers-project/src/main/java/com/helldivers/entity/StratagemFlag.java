package com.helldivers.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class StratagemFlag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flagId;
    private String name;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "stratagem_flag_links",
            joinColumns = @JoinColumn(name = "flag_id"),
            inverseJoinColumns = @JoinColumn(name = "stratagem_id")
    )
    private Set<Stratagem> stratagems = new HashSet<>();
}
