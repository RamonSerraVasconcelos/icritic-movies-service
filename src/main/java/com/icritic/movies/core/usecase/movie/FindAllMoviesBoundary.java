package com.icritic.movies.core.usecase.movie;

import com.icritic.movies.core.model.Movie;
import com.icritic.movies.core.model.MovieFilter;

import java.util.List;

public interface FindAllMoviesBoundary {

    List<Movie> execute(MovieFilter movieFilter);
}
