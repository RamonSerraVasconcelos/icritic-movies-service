package com.icritic.movies.core.usecase.movie;

import com.icritic.movies.core.model.Movie;

import java.util.Optional;

public interface FindMovieByIdBoundary {

    Optional<Movie> execute(Long id);
}
