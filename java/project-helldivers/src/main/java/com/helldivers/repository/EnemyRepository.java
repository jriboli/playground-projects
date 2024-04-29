package com.helldivers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.helldivers.entity.enemies.Enemy;

public interface EnemyRepository extends JpaRepository<Enemy, Long> {

}
