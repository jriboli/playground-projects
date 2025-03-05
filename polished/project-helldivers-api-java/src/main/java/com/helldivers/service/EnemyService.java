package com.helldivers.service;

import com.helldivers.entity.enemies.Enemy;
import com.helldivers.enums.enemies.Type;
import com.helldivers.model.enemies.EnemyData;
import com.helldivers.model.enemies.EnemyResponse;
import com.helldivers.model.enemies.EnemyStatusData;
import com.helldivers.repository.EnemyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
@Slf4j
public class EnemyService {
    private EnemyRepository repository;

    public EnemyService(EnemyRepository repository) {
        this.repository = repository;
    }
    public EnemyResponse getEnemies() {
        log.info("Retrieving enemies from database.");
        List<Enemy> results = repository.findAll();
        log.info("DB Results: " + results.toString());
        List<EnemyData> enemyResponse = results.stream()
                .map(EnemyData::new)
                .toList();

        return new EnemyResponse(enemyResponse);
    }

    public EnemyResponse getEnemiesWithFilter(Type type) {
        log.info("Retrieving enemies from database.");
        List<Enemy> results = repository.findAll();

        List<EnemyData> enemyResponse = results.stream()
                .map(EnemyData::new)
                .toList();

        if(!Objects.isNull(type)) {
            enemyResponse = enemyResponse.stream()
                    .filter(w -> w.getType().equals(type))
                    .toList();
        }

        return new EnemyResponse(enemyResponse);
    }

    public EnemyResponse getEnemyById(Long enemyId) {
        Enemy result = findOrCreateEnemy(enemyId);
        return new EnemyResponse(List.of(new EnemyData(result)));
    }

    public EnemyResponse saveEnemy(EnemyData enemyData) {
        Long enemyId = enemyData.getId();
        Enemy enemy = findOrCreateEnemy(enemyId);

        setFieldsInEnemy(enemy, enemyData);
        return new EnemyResponse(new EnemyData(repository.save(enemy)));
    }

    public EnemyResponse updateEnemyStatus(Long enemyId, EnemyStatusData enemyStatus) {
        Enemy result = findOrCreateEnemy(enemyId);
        // TBD
        return null;
    }

    public void deleteEnemy(Long enemyId) {
        Enemy result = findOrCreateEnemy(enemyId);
        repository.delete(result);
    }

    private Enemy findOrCreateEnemy(Long enemyId) {
        Enemy enemy;
        if(Objects.isNull(enemyId)) {
            enemy = new Enemy();
        }
        else {
            enemy = findEnemyById(enemyId);
        }

        return enemy;
    }

    private Enemy findEnemyById(Long enemyId) {
        return repository.findById(enemyId).orElseThrow(() -> new NoSuchElementException("No matching enemy found with that Id."));
    }

    private void setFieldsInEnemy(Enemy enemy, EnemyData data) {
        enemy.setId(data.getId());
        enemy.setName(data.getName());
        enemy.setDescription(data.getDescription());
        enemy.setType(data.getType());
    }
}
