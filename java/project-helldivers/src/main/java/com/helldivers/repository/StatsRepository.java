package com.helldivers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.helldivers.entity.matches.Stats;

public interface StatsRepository extends JpaRepository<Stats, Long> {

}
