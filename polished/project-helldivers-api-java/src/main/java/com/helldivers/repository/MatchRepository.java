package com.helldivers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.helldivers.entity.matches.Match;

public interface MatchRepository extends JpaRepository<Match, Long> {

}
