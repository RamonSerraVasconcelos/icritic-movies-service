package com.icritic.movies.dataprovider.database.repository;

import com.icritic.movies.dataprovider.database.entity.MovieEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieEntityRepository extends JpaRepository<MovieEntity, Long> {

    Page<MovieEntity> findAllByActiveOrderByIdAsc(Pageable pageable, boolean active);
}
