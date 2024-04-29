package com.helldivers.entity.weapons;

import com.helldivers.entity.matches.Kills;
import com.helldivers.enums.weapons.Mode;
import com.helldivers.enums.weapons.Position;
import com.helldivers.enums.weapons.Trait;
import com.helldivers.enums.weapons.Type;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@Entity
public class Weapon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private String description;
    private Type type;
    private Integer damage;
    private Integer capacity;
    private Integer recoil;
    private Integer fireRate;
    private Position position;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Mode.class)
    @CollectionTable(name = "weapon_modes", joinColumns = @JoinColumn(name = "weapon_id"))
    @Column(name = "mode")
    private Set<Mode> modes;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Trait.class)
    @CollectionTable(name = "weapon_traits", joinColumns = @JoinColumn(name = "weapon_id"))
    @Column(name = "trait")
    private Set<Trait> traits;

    @OneToMany(mappedBy = "weapon")
    private Set<Kills> kills;
}
