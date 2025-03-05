package com.helldivers.repository;

import com.helldivers.entity.players.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {

}
