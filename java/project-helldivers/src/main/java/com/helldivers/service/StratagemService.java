package com.helldivers.service;

import com.helldivers.entity.stratagems.Stratagem;
import com.helldivers.entity.stratagems.Flag;
import com.helldivers.model.stratagems.StratagemData;
import com.helldivers.model.stratagems.FlagData;
import com.helldivers.repository.FlagRepository;
import com.helldivers.repository.StratagemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
@Slf4j
public class StratagemService {

    private StratagemRepository stratagemRepository;
    private FlagRepository flagRepository;

    public StratagemService(StratagemRepository stmRepo, FlagRepository flgRepo) {
        this.flagRepository = flgRepo;
        this.stratagemRepository = stmRepo;
    }

    public List<StratagemData> getAllStratagems() {
        List<Stratagem> stratagems = stratagemRepository.findAll();
        log.info("Stratagems: " + stratagems.toString());

        return stratagems.stream()
                .map(s -> new StratagemData(s))
                .toList();
    }

    public List<StratagemData> getAllStratagemsByCategory(String category) {
        List<Stratagem> stratagems = stratagemRepository.findByCategory(category);
        log.info("Stratagems: " + stratagems.toString());

        return stratagems.stream()
                .map(s -> new StratagemData(s))
                .toList();
    }

    public List<StratagemData> getAllStratagemsByFlag(String flagName) {
        Flag flag = findFlagByName(flagName);
        List<Stratagem> stratagems = stratagemRepository.findByFlags(flag);
        log.info("Stratagems: " + stratagems.toString());

        return stratagems.stream()
                .map(s -> new StratagemData(s))
                .toList();
    }

    public StratagemData getStratagemById(Long stratagemId) {
        Stratagem stratagem = findOrCreateStratagem(stratagemId);
        return new StratagemData(stratagem);
    }

    public StratagemData saveStratagem(StratagemData data) {
        Long stratagemId = data.getStratagemId();
        Stratagem stratagem = findOrCreateStratagem(stratagemId);

        setFieldsInStratagem(stratagem, data);
        return new StratagemData(stratagemRepository.save(stratagem));
    }

    public void deleteStratagem(Long stratagemId) {
        Stratagem stratagem = findOrCreateStratagem(stratagemId);

        stratagemRepository.delete(stratagem);
    }

    public List<FlagData> getAllFlags() {
        List<Flag> stratagemFlags = flagRepository.findAll();

        return stratagemFlags.stream()
                .map(FlagData::new)
                .toList();
    }

    public FlagData getFlagById(Long flagId) {
        Flag stratagemFlag = findOrCreateFlag(flagId);
        return new FlagData(stratagemFlag);
    }

    public FlagData saveFlag(FlagData data) {
        return null;
    }

    // PRIVATE
    private Stratagem findOrCreateStratagem(Long stratagemId) {
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
        return stratagemRepository.findById(stratagemId).orElseThrow(() -> new NoSuchElementException("No Stratagem matching that Id."));
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
        boolean hasFlags = !Objects.isNull(data.getFlags());

        if(hasFlags) {
            for(FlagData flagData : data.getFlags()) {
                Flag flag = flagRepository.findByName(flagData.getName()).orElseThrow(() -> new NoSuchElementException("No Flag found with that name."));
                stratagem.getFlags().add(flag);
            }
        }
    }

    private Flag findOrCreateFlag(Long flagId) {
        Flag flag;
        if(Objects.isNull(flagId)) {
            flag = new Flag();
        }
        else {
            flag = findFlagById(flagId);
        }

        return flag;
    }

    private Flag findFlagById(Long flagId) {
        return flagRepository.findById(flagId).orElseThrow(() -> new NoSuchElementException("No Flag matching that Id."));
    }

    private Flag findFlagByName(String flagName) {
        return flagRepository.findByName(flagName).orElseThrow(() -> new NoSuchElementException("No Flag found with that name."));
    }
}
