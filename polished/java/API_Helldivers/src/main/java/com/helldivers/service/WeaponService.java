package com.helldivers.service;

import com.helldivers.entity.weapons.Weapon;
import com.helldivers.enums.weapons.Mode;
import com.helldivers.enums.weapons.Position;
import com.helldivers.enums.weapons.Trait;
import com.helldivers.enums.weapons.Type;
import com.helldivers.model.weapons.WeaponData;
import com.helldivers.model.weapons.WeaponResponse;
import com.helldivers.model.weapons.WeaponStatusData;
import com.helldivers.repository.WeaponRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
@Slf4j
public class WeaponService {

    private WeaponRepository repository;

    public WeaponService(WeaponRepository repository) {
        this.repository = repository;
    }
    public WeaponResponse getWeapons() {
        log.info("Retrieving weapons from database.");
        List<Weapon> results = repository.findAll();
        log.info("DB Results: " + results.toString());
        List<WeaponData> weaponResponse = results.stream()
                .map(WeaponData::new)
                .toList();

        return new WeaponResponse(weaponResponse);
    }

    public WeaponResponse getWeaponsWithFilter(Trait trait, Mode mode, Position position, Type type) {
        log.info("Retrieving weapons from database.");
        List<Weapon> results = repository.findAll();

        List<WeaponData> weaponResponse = results.stream()
                .map(WeaponData::new)
                .toList();

        if(!Objects.isNull(trait)) {
            weaponResponse = weaponResponse.stream()
                    .filter(w -> w.getTraits().contains(trait))
                    .toList();
        }

        if(!Objects.isNull(mode)) {
            weaponResponse = weaponResponse.stream()
                    .filter(w -> w.getModes().contains(mode))
                    .toList();
        }

        if(!Objects.isNull(position)) {
            weaponResponse = weaponResponse.stream()
                    .filter(w -> w.getPosition().equals(position))
                    .toList();
        }

        if(!Objects.isNull(type)) {
            weaponResponse = weaponResponse.stream()
                    .filter(w -> w.getType().equals(type))
                    .toList();
        }

        return new WeaponResponse(weaponResponse);
    }

    public WeaponResponse getWeaponById(Long weaponId) {
        Weapon result = findOrCreateWeapon(weaponId);
        return new WeaponResponse(List.of(new WeaponData(result)));
    }

    public WeaponResponse saveWeapon(WeaponData weaponData) {
        Long weaponId = weaponData.getId();
        Weapon weapon = findOrCreateWeapon(weaponId);

        setFieldsInWeapon(weapon, weaponData);
        return new WeaponResponse(new WeaponData(repository.save(weapon)));
    }

    public WeaponResponse updateWeaponStatus(Long weaponId, WeaponStatusData weaponStatus) {
        Weapon result = findOrCreateWeapon(weaponId);
        // TBD
        return null;
    }

    public void deleteWeapon(Long weaponId) {
        Weapon result = findOrCreateWeapon(weaponId);
        repository.delete(result);
    }

    private Weapon findOrCreateWeapon(Long weaponId) {
        Weapon weapon;
        if(Objects.isNull(weaponId)) {
            weapon = new Weapon();
        }
        else {
            weapon = findWeaponById(weaponId);
        }

        return weapon;
    }

    private Weapon findWeaponById(Long weaponId) {
        return repository.findById(weaponId).orElseThrow(() -> new NoSuchElementException("No matching weapon found with that Id."));
    }

    private void setFieldsInWeapon(Weapon weapon, WeaponData data) {
        weapon.setId(data.getId());
        weapon.setName(data.getName());
        weapon.setDescription(data.getDescription());
        weapon.setType(data.getType());
        weapon.setDamage(data.getDamage());
        weapon.setCapacity(data.getCapacity());
        weapon.setRecoil(data.getRecoil());
        weapon.setFireRate(data.getFire_rate());
        weapon.setPosition(data.getPosition());
        weapon.setModes(data.getModes());
        weapon.setTraits(data.getTraits());
    }
}
