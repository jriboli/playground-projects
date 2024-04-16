package com.helldivers.service;

import com.helldivers.entity.Stratagem;
import com.helldivers.model.StratagemData;
import com.helldivers.repository.StratagemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
@Slf4j
public class StratagemService {

    private StratagemRepository stmRepo;

    public StratagemService(StratagemRepository stmRepo) {
        this.stmRepo = stmRepo;
    }

    public List<StratagemData> getAllStratagems() {
        List<Stratagem> stratagems = stmRepo.findAll();

        return stratagems.stream()
                .map(s -> new StratagemData(s))
                .toList();
    }

    public StratagemData getStratagemById(Long stratagemId) {
        Stratagem stratagem = stmRepo.getReferenceById(stratagemId);

        return new StratagemData(stratagem);
    }

    public void createStratagem(StratagemData data) {
        Stratagem stratagem = new Stratagem();
        stmRepo.save(stratagem);

    }

    // PRIVATE
    private Stratagem findOrCreate(Long stratagemId) {
        Stratagem stratagem;
        if(Objects.isNull(stratagemId)){
            stratagem = new Stratagem();
        }
        else {
            stratagem = findStratagemById(stratagemId);
        }

        return stratagem;
    }

    private Stratagem findStratagemById(Long stratagemId) {
        return stmRepo.findById(stratagemId).orElseThrow(() -> new NoSuchElementException("No Stratagem mathcing that Id."));
    }

    private void setFieldsInStratagem(Stratagem stratagem, StratagemData data) {
        stratagem.setStratagemId(data.getStratagemId());
        stratagem.setCategory(data.getCategory());
        stratagem.setCode(data.getCode());
        stratagem.setName(data.getName());
        stratagem.setDescription(data.getDescription());
        stratagem.setCooldown(data.getCooldown());
        stratagem.setUses(data.getUses());
        stratagem.setType(data.getType());
        stratagem.setSpawnTime(data.getSpawnTime());

        // Flag ManyToMany complexity
        stratagem.setFlags(data.getFlags());
    }

    @
}
