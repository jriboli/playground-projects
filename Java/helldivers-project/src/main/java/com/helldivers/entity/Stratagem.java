package com.helldivers.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Stratagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stratagemId;
    private String name;
    @Column(length = 1000)
    private String description;
    private String code;
    private String category;
    private String type;
    private int uses;
    private int spawnTime;
    private int cooldown;

    //ManyToMany relationship with Flag - Unidirectional
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "stratagem_flag_links",
            joinColumns = @JoinColumn(name = "stratagem_id"),
            inverseJoinColumns = @JoinColumn(name = "flag_id")
    )
    private Set<StratagemFlag> flags = new HashSet<>();

    public Stratagem(String name, String description, String code, String category, String type, int uses, int spawnTime, int cooldown, Set<StratagemFlag> flags) {
        this.name = name;
        this.description = description;
        this.code = code;
        this.category = category;
        this.type = type;
        this.uses = uses;
        this.spawnTime = spawnTime;
        this.cooldown = cooldown;
        this.flags = flags;
    }

    public Stratagem() {

    }
}
