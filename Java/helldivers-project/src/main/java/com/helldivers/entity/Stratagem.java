package com.helldivers.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Stratagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stratagemId;
    private String name;
    private String description;
    private String code;
    private String category;
    private String type;
    private int uses;
    private int spawnTime;
    private int cooldown;

    //OneToMany
    @ManyToMany(mappedBy = "stratagems", cascade = CascadeType.ALL)
    private Set<StratagemFlag> flags = new HashSet<>();

    public Stratagem(String name, String description, String code, String category, String type, int uses, int spawnTime, int cooldown) {
        this.name = name;
        this.description = description;
        this.code = code;
        this.category = category;
        this.type = type;
        this.uses = uses;
        this.spawnTime = spawnTime;
        this.cooldown = cooldown;
    }

    public Stratagem() {

    }
}
