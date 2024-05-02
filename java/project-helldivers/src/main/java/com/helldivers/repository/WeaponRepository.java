package com.helldivers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.helldivers.entity.weapons.Weapon;

import java.util.Optional;

public interface WeaponRepository extends JpaRepository<Weapon, Long> {

    Optional<Weapon> findByName(String name);

}
