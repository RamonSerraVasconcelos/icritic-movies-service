package com.icritic.movies.dataprovider.database.repository;

import com.icritic.movies.dataprovider.database.entity.ActorEntity;

import java.util.List;

public interface ActorEntityCustomRepository {

    List<ActorEntity> findByMovieId(Long movieId);
}
