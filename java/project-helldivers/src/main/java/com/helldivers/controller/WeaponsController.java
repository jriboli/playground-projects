package com.helldivers.controller;

import com.helldivers.model.PlayerData;
import com.helldivers.model.PlayerResponse;
import com.helldivers.model.WeaponData;
import com.helldivers.model.WeaponResponse;
import com.helldivers.service.WeaponService;
import jakarta.persistence.Entity;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/weapons")
public class WeaponsController {

    private WeaponService service;

    @GetMapping
    public ResponseEntity<WeaponResponse> getWeapons() {

        return ResponseEntity.ok(service.getWeapons());
    }

    @GetMapping("/{weaponId}")
    public ResponseEntity<WeaponResponse> getWeaponById(@PathVariable Long weaponId) {

        return ResponseEntity.ok(service.getWeaponById(weaponId));
    }

    @PostMapping
    public ResponseEntity<WeaponResponse> createWeapon(@RequestBody WeaponData weaponData) {

        return ResponseEntity.ok(service.createWeapon(weaponData));
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
