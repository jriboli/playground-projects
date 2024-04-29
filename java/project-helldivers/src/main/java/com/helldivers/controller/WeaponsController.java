package com.helldivers.controller;

import com.helldivers.enums.weapons.Mode;
import com.helldivers.enums.weapons.Position;
import com.helldivers.enums.weapons.Trait;
import com.helldivers.enums.weapons.Type;
import com.helldivers.model.weapons.WeaponData;
import com.helldivers.model.weapons.WeaponResponse;
import com.helldivers.model.weapons.WeaponStatusData;
import com.helldivers.service.WeaponService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/weapons")
@Slf4j
public class WeaponsController {

    private WeaponService service;

    public WeaponsController(WeaponService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<WeaponResponse> getWeapons(
            @RequestParam(required = false) Optional<String> trait,
            @RequestParam(required = false) Optional<String> mode,
            @RequestParam(required = false) Optional<String> position,
            @RequestParam(required = false) Optional<String> type
    ) {

        log.info("Calling Get All Weapons.");
        WeaponResponse response;
        if(trait.isPresent() || mode.isPresent() || position.isPresent() || type.isPresent()) {
            Trait tTrait = trait.isPresent() ? Trait.valueOf(trait.get()) : null;
            Mode tMode = mode.isPresent() ? Mode.valueOf(mode.get()) : null;
            Position tPosition = position.isPresent() ? Position.valueOf(position.get()) : null;
            Type tType = type.isPresent() ? Type.valueOf(type.get()) : null;
            response = service.getWeaponsWithFilter(tTrait, tMode, tPosition, tType);
        } else {
            response = service.getWeapons();
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{weaponId}")
    public ResponseEntity<WeaponResponse> getWeaponById(@PathVariable Long weaponId) {

        return ResponseEntity.ok(service.getWeaponById(weaponId));
    }

    @PostMapping
    public ResponseEntity<WeaponResponse> createWeapon(@RequestBody WeaponData weaponData) {

        return ResponseEntity.ok(service.saveWeapon(weaponData));
    }

    // Might need to replace this with a make inactive functionality
    // We dont want to be updating a weapon that has already been logged into by Player used
    @PatchMapping("/{weaponId}/status")
    public ResponseEntity<WeaponResponse> updateWeapon(@RequestBody WeaponStatusData weaponStatus, @PathVariable Long weaponId) {

        return ResponseEntity.ok(service.updateWeaponStatus(weaponId, weaponStatus));
    }

    @DeleteMapping("/{weaponId}")
    public ResponseEntity<String> deleteWeapon(@PathVariable Long weaponId) {

        service.deleteWeapon(weaponId);
        return ResponseEntity.ok("Strategem removed.");
    }
}
