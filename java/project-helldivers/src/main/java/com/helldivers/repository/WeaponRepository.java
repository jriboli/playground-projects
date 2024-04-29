package com.helldivers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.helldivers.entity.weapons.Weapon;

public interface WeaponRepository extends JpaRepository<Weapon, Long> {

}
