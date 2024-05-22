package com.icritic.movies.dataprovider.database.repository;

import com.icritic.movies.dataprovider.database.entity.ActorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorEntityRepository extends JpaRepository<ActorEntity, Long>, ActorEntityCustomRepository {

    Page<ActorEntity> findAllByOrderByIdAsc(Pageable pageable);
}
