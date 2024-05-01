package com.helldivers.entity.matches;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @OneToMany(mappedBy = "match")
//    private List<Kills> kills;

    private String matchType;
    private String location;


}
