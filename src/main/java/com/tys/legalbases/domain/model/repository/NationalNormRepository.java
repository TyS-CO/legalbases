package com.tys.legalbases.domain.model.repository;

import com.tys.legalbases.domain.model.entity.NationalNormEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NationalNormRepository
        extends JpaRepository<NationalNormEntity, Long>,
                JpaSpecificationExecutor<NationalNormEntity> {

    Optional<NationalNormEntity> findByTitle(String title);
}
