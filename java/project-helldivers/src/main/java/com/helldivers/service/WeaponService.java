package com.helldivers.service;

import com.helldivers.entity.weapons.Weapon;
import com.helldivers.model.WeaponData;
import com.helldivers.model.WeaponResponse;
import com.helldivers.model.WeaponStatusData;
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
    public WeaponResponse getWeapons() {
        List<Weapon> results = repository.findAll();
        List<WeaponData> weaponResponse = results.stream()
                .map(w -> new WeaponData(w))
                .toList();

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
        weapon.setFireRate(data.getFireRate());
        weapon.setPosition(data.getPosition());
        weapon.setModes(data.getModes());
        weapon.setTraits(data.getTraits());
    }

}
