package com.helldivers.entity.players;

import com.helldivers.enums.player.Type;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    @Column(unique = true)
    private String gamerTag;

    @Enumerated(EnumType.STRING)
    private Type type;

//    @OneToMany(mappedBy = "player")
//    private List<Kills> kills;
}
