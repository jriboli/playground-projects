package com.helldivers.entity.enemies;

import com.helldivers.enums.enemies.Type;
import jakarta.persistence.*;
import lombok.Data;

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
