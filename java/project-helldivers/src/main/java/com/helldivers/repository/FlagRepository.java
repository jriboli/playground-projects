package com.helldivers.repository;

import com.helldivers.entity.stratagems.Flag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FlagRepository extends JpaRepository<Flag, Long> {

    Optional<Flag> findByName(String name);
}
