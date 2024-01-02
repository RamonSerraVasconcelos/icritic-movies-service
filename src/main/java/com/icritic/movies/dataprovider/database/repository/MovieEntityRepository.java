package com.icritic.movies.dataprovider.database.repository;

import com.icritic.movies.dataprovider.database.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieEntityRepository extends JpaRepository<MovieEntity, Long> {

    List<MovieEntity> findAllByActiveOrderByIdAsc(boolean active);
}
