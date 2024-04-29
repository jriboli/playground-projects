package com.helldivers.service;

import com.helldivers.entity.weapons.Weapon;
import com.helldivers.model.PlayerResponse;
import com.helldivers.model.WeaponData;
import com.helldivers.model.WeaponResponse;
import com.helldivers.repository.WeaponRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WeaponService {

    private WeaponRepository repository;
    public WeaponResponse getWeapons() {

    }

    public PlayerResponse getWeaponById(Long weaponId) {
    }

    public PlayerResponse createWeapon(WeaponData weaponData) {
    }

    public PlayerResponse updateWeaponStatus(Long weaponId, WeaponStatusData weaponStatus) {
    }

    public void deleteWeapon(Long weaponId) {
    }
}
