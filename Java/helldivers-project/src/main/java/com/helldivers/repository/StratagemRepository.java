package com.helldivers.repository;

import com.helldivers.entity.Stratagem;
import com.helldivers.entity.StratagemFlag;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StratagemRepository extends JpaRepository<Stratagem, Long> {

    List<Stratagem> findByCategory(String category);

    List<Stratagem> findByFlags(StratagemFlag flag);
}
