package com.icritic.movies.dataprovider.database.repository;

import com.icritic.movies.dataprovider.database.entity.DirectorEntity;

import java.util.List;

public interface DirectorEntityCustomRepository {

    List<DirectorEntity> findMovieCategories(Long movieId);
}
