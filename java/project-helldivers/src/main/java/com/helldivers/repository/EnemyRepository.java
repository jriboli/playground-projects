package com.helldivers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.helldivers.entity.enemies.Enemy;

import java.util.Optional;

public interface EnemyRepository extends JpaRepository<Enemy, Long> {

    Optional<Enemy> findByName(String name);

}
