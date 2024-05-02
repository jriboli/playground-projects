package com.helldivers.repository;

import com.helldivers.entity.matches.Kill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KillRepository extends JpaRepository<Kill, Long> {

}
