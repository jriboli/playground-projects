package com.helldivers.service;

import com.helldivers.entity.enemies.Enemy;
import com.helldivers.entity.matches.Kill;
import com.helldivers.entity.matches.Match;
import com.helldivers.entity.players.Player;
import com.helldivers.entity.weapons.Weapon;
import com.helldivers.enums.matches.Location;
import com.helldivers.enums.matches.Objective;
import com.helldivers.model.matches.KillData;
import com.helldivers.model.matches.KillResponse;
import com.helldivers.model.matches.MatchData;
import com.helldivers.model.matches.MatchResponse;
import com.helldivers.repository.*;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class MatchService {

    private MatchRepository matchRepo;
    private KillRepository killRepo;
    private PlayerRepository playerRepo;

    private EnemyRepository enemyRepo;
    private WeaponRepository weaponRepo;

    public MatchService(MatchRepository matchRepo, KillRepository killRepo, PlayerRepository playerRepo
            , WeaponRepository weaponRepo, EnemyRepository enemyRepo) {
        this.matchRepo = matchRepo;
        this.killRepo = killRepo;
        this.playerRepo = playerRepo;
        this.enemyRepo = enemyRepo;
        this.weaponRepo = weaponRepo;
    }

    public MatchResponse saveMatch(MatchData matchData) {

        Long matchId = matchData.getId();
        Match match = findOrCreateMatch(matchId);

        setFieldsInMatch(match, matchData);
        return new MatchResponse(new MatchData(matchRepo.save(match)));
    }

    private Match findOrCreateMatch(Long matchId) {
        Match match;
        if(Objects.isNull(matchId)) {
            match = new Match();
        }
        else {
            match = findMatchById(matchId);
        }

        return match;
    }
    private Match findMatchById(Long matchId) {
        return matchRepo.findById(matchId).orElseThrow(() -> new NoSuchElementException("No matching match found with that Id."));
    }

    private void setFieldsInMatch(Match match, MatchData data) {
        match.setId(data.getId());
        match.setLocation(Location.valueOf(data.getLocation()));
        match.setObjective(Objective.valueOf(data.getObjective()));

    }



    /// ********************* KILLS SECTION **********************************

    public KillResponse addKillToMatch(Long matchId, KillData killData) {
        Long killId = killData.getId();
        Kill kill = findOrCreateKill(killId);

        setFieldsInKill(kill, killData, matchId);
        return new KillResponse(new KillData(killRepo.save(kill)));
    }

    private Kill findOrCreateKill(Long killId) {
        Kill kill;
        if(Objects.isNull(killId)) {
            kill = new Kill();
        }
        else {
            kill = findKillById(killId);
        }

        return kill;
    }

    private Kill findKillById(Long killId) {
        return killRepo.findById(killId).orElseThrow(() -> new NoSuchElementException("No matching kill found with that Id."));
    }

    private void setFieldsInKill(Kill kill, KillData data, Long matchId) {
        Match match = findOrCreateMatch(matchId);
        Player player = findPlayerById(data.getPlayer_id());
        Enemy enemy = findEnemyByName(data.getEnemy_name());
        Weapon weapon = findWeaponByName(data.getWeapon_name());

        kill.setMatch(match);
        kill.setPlayer(player);
        kill.setTimeOfKill(data.getTime_of_kill());
        kill.setEnemy(enemy);
        kill.setWeapon(weapon);
    }

    private Player findPlayerById(Long PlayerId) {
        return playerRepo.findById(PlayerId).orElseThrow(() -> new NoSuchElementException("No matching Player found with that Id."));
    }

    private Enemy findEnemyByName(String name) {
        return enemyRepo.findByName(name).orElseThrow(() -> new NoSuchElementException("No matching enemy found with that Name."));
    }

    private Weapon findWeaponByName(String name) {
        return weaponRepo.findByName(name).orElseThrow(() -> new NoSuchElementException("No matching weapon found with that Name."));
    }
}
