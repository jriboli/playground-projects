package com.helldivers.repository;

import com.helldivers.entity.StratagemFlag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FlagRepository extends JpaRepository<StratagemFlag, Long> {

    Optional<StratagemFlag> findByName(String name);
}
