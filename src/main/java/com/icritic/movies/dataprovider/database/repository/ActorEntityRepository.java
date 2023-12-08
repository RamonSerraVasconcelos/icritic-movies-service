package com.icritic.movies.dataprovider.database.repository;

import com.icritic.movies.dataprovider.database.entity.ActorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActorEntityRepository extends JpaRepository<ActorEntity, Long> {

    List<ActorEntity> findAllByOrderByIdAsc();
}
