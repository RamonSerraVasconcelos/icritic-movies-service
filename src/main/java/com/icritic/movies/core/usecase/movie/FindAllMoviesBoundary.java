package com.icritic.movies.core.usecase.movie;

import com.icritic.movies.core.model.Movie;

import java.util.List;

public interface FindAllMoviesBoundary {

    List<Movie> execute();
}
