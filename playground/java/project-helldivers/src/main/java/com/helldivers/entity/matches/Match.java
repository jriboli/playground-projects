package com.helldivers.entity.matches;

import com.helldivers.enums.matches.Location;
import com.helldivers.enums.matches.Objective;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "match_records")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @OneToMany(mappedBy = "match")
//    private List<Kills> kills;

    @Enumerated(EnumType.STRING)
    private Objective objective;

    @Enumerated(EnumType.STRING)
    private Location location;


}
