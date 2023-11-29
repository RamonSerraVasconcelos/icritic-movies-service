package com.icritic.movies.dataprovider.database.repository;

import com.icritic.movies.dataprovider.database.entity.DirectorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectorEntityRepository extends JpaRepository<DirectorEntity, Long> {
}
