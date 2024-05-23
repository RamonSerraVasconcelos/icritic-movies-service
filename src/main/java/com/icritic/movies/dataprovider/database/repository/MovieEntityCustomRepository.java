package com.icritic.movies.dataprovider.database.repository;

import com.icritic.movies.core.model.MovieFilter;
import com.icritic.movies.dataprovider.database.entity.MovieEntity;

import java.util.List;

public interface MovieEntityCustomRepository {

    List<MovieEntity> findByParams(MovieFilter movieFilterParams);

    Long countMovies(MovieFilter movieFilterParams);
}
