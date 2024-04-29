package com.helldivers.entity.enemies;

import com.helldivers.entity.matches.Kills;
import com.helldivers.enums.enemies.Type;
import com.helldivers.enums.weapons.Trait;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Enemy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @Enumerated(EnumType.STRING)
    private Type type;

//    @OneToMany(mappedBy = "enemy")
//    private List<Kills> kills;
}
