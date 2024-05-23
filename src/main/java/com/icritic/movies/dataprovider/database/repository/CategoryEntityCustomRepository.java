package com.icritic.movies.dataprovider.database.repository;

import com.icritic.movies.dataprovider.database.entity.CategoryEntity;

import java.util.List;

public interface CategoryEntityCustomRepository {

    List<CategoryEntity> findByMovieId(Long movieId);
}
