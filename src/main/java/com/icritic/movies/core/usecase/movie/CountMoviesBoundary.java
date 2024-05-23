package com.icritic.movies.core.usecase.movie;

import com.icritic.movies.core.model.MovieFilter;

public interface CountMoviesBoundary {

    Long execute(MovieFilter movieFilter);
}
