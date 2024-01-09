package com.icritic.movies.dataprovider.database.repository;

import com.icritic.movies.dataprovider.database.entity.DirectorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DirectorEntityRepository extends JpaRepository<DirectorEntity, Long> {

    Page<DirectorEntity> findAllByOrderByIdAsc(Pageable pageable);
}
