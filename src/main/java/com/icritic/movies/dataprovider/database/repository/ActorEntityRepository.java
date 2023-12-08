package com.icritic.movies.dataprovider.database.repository;

import com.icritic.movies.dataprovider.database.entity.ActorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorEntityRepository extends JpaRepository<ActorEntity, Long> {
}
