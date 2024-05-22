package com.icritic.movies.core.usecase.movie;

import com.icritic.movies.core.model.Category;

import java.util.List;

public interface FindMovieCategoriesBoundary {

    List<Category> execute(Long movieId);
}
