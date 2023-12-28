package com.icritic.movies.core.usecase.movie;

import com.icritic.movies.core.model.Movie;

public interface SaveMovieBoundary {

    Movie execute(Movie movie);
}
