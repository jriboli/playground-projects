package com.helldivers.service;

import com.helldivers.entity.players.Player;
import com.helldivers.enums.player.Type;
import com.helldivers.model.PlayerData;
import com.helldivers.model.PlayerResponse;
import com.helldivers.repository.PlayerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
@Slf4j
public class PlayerService {
    private PlayerRepository repository;

    public PlayerService(PlayerRepository repository) {
        this.repository = repository;
    }
    public PlayerResponse getPlayers() {
        log.info("Retrieving Players from database.");
        List<Player> results = repository.findAll();
        log.info("DB Results: " + results.toString());
        List<PlayerData> PlayerResponse = results.stream()
                .map(PlayerData::new)
                .toList();

        return new PlayerResponse(PlayerResponse);
    }

    public PlayerResponse getPlayersWithFilter(Type type) {
        log.info("Retrieving Players from database.");
        List<Player> results = repository.findAll();

        List<PlayerData> PlayerResponse = results.stream()
                .map(PlayerData::new)
                .toList();

        if(!Objects.isNull(type)) {
            PlayerResponse = PlayerResponse.stream()
                    .filter(w -> w.getType().equals(type))
                    .toList();
        }

        return new PlayerResponse(PlayerResponse);
    }

    public PlayerResponse getPlayerById(Long PlayerId) {
        Player result = findOrCreatePlayer(PlayerId);
        return new PlayerResponse(List.of(new PlayerData(result)));
    }

    public PlayerResponse savePlayer(PlayerData PlayerData) {
        Long PlayerId = PlayerData.getId();
        Player Player = findOrCreatePlayer(PlayerId);

        setFieldsInPlayer(Player, PlayerData);
        return new PlayerResponse(new PlayerData(repository.save(Player)));
    }

    public void deletePlayer(Long PlayerId) {
        Player result = findOrCreatePlayer(PlayerId);
        repository.delete(result);
    }

    private Player findOrCreatePlayer(Long PlayerId) {
        Player Player;
        if(Objects.isNull(PlayerId)) {
            Player = new Player();
        }
        else {
            Player = findPlayerById(PlayerId);
        }

        return Player;
    }

    private Player findPlayerById(Long PlayerId) {
        return repository.findById(PlayerId).orElseThrow(() -> new NoSuchElementException("No matching Player found with that Id."));
    }

    private void setFieldsInPlayer(Player Player, PlayerData data) {
        Player.setId(data.getId());
        Player.setName(data.getName());
        Player.setEmail(data.getEmail());
        Player.setGamerTag(data.getGamerTag());
        Player.setType(data.getType());
    }
}
