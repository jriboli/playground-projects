package com.binaryNomad.designpatternsstrategyv2.controller;

import com.binaryNomad.designpatternsstrategyv2.model.*;
import com.binaryNomad.designpatternsstrategyv2.service.ShopKeeper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/shopkeeper")
public class ShopKeeperController {

    private ShopKeeper shopKeeper;
    public ShopKeeperController(ShopKeeper shopKeeper) {
        this.shopKeeper = shopKeeper;
    }

    @PostMapping("/buy")
    public ResponseEntity<String> buyItem(
            @RequestBody ItemData item,
            @RequestParam(required = false) Optional<String> attitude) {

        // Check if Attitude is valid
        AttitudeStrategy strategy = setStrategy(attitude.get());

        // Build ShopKeeper
        shopKeeper.setAttitudeStrategy(strategy);
        return ResponseEntity.ok(shopKeeper.buyItem(item.getItemPrice()));
    }

    @PostMapping("/sell")
    public ResponseEntity<String> sellItem(
            @RequestBody ItemData item,
            @RequestParam(required = false) Optional<String> attitude) {

        // Check if Attitude is valid
        AttitudeStrategy strategy = setStrategy(attitude.get());

        // Build ShopKeeper
        shopKeeper.setAttitudeStrategy(strategy);
        return ResponseEntity.ok(shopKeeper.sellItem(item.getItemPrice()));
    }

    private AttitudeStrategy setStrategy(String attitude) {
        switch(attitude) {
            case "giving":
                return new GivingNPC();
            case "stingy":
                return new StingyNPC();
            default:
                return new NeutralNPC();
        }
    }
}
