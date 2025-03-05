package com.helldivers.repository;

import com.helldivers.entity.stratagems.Stratagem;
import com.helldivers.entity.stratagems.Flag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StratagemRepository extends JpaRepository<Stratagem, Long> {

    List<Stratagem> findByCategory(String category);

    List<Stratagem> findByFlags(Flag flag);
}
