package com.icritic.movies.core.usecase.movie;

public interface UpsertMovieRateBoundary {

    void execute(Long movieId, Long userId, int rating);
}
