package com.helldivers.service;

import com.helldivers.entity.Stratagem;
import com.helldivers.entity.StratagemFlag;
import com.helldivers.model.StratagemData;
import com.helldivers.model.StratagemFlagData;
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
        StratagemFlag flag = findFlagByName(flagName);
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

    public List<StratagemFlagData> getAllFlags() {
        List<StratagemFlag> stratagemFlags = flagRepository.findAll();

        return stratagemFlags.stream()
                .map(StratagemFlagData::new)
                .toList();
    }

    public StratagemFlagData getFlagById(Long flagId) {
        StratagemFlag stratagemFlag = findOrCreateFlag(flagId);
        return new StratagemFlagData(stratagemFlag);
    }

    public StratagemFlagData saveFlag(StratagemFlagData data) {
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
            for(StratagemFlagData flagData : data.getFlags()) {
                StratagemFlag flag = flagRepository.findByName(flagData.getName()).orElseThrow(() -> new NoSuchElementException("No Flag found with that name."));
                stratagem.getFlags().add(flag);
            }
        }
    }

    private StratagemFlag findOrCreateFlag(Long flagId) {
        StratagemFlag flag;
        if(Objects.isNull(flagId)) {
            flag = new StratagemFlag();
        }
        else {
            flag = findFlagById(flagId);
        }

        return flag;
    }

    private StratagemFlag findFlagById(Long flagId) {
        return flagRepository.findById(flagId).orElseThrow(() -> new NoSuchElementException("No Flag matching that Id."));
    }

    private StratagemFlag findFlagByName(String flagName) {
        return flagRepository.findByName(flagName).orElseThrow(() -> new NoSuchElementException("No Flag found with that name."));
    }
}
