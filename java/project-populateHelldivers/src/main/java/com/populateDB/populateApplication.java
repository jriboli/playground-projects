package com.populateDB;

import com.helldivers.Application;
import com.populateDB.players.PlayerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class populateApplication {

    public static void main(String[] args) {
        SpringApplication.run(populateApplication.class, args);

        PlayerService service = new PlayerService();
        service.genRandomPlayerInfo();
    }
}
