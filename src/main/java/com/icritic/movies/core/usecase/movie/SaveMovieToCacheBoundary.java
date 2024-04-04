package com.icritic.movies.core.usecase.movie;

import com.icritic.movies.core.model.Movie;

public interface SaveMovieToCacheBoundary {

    void execute(Movie movie);
}
