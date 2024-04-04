package com.icritic.movies.core.usecase.movie;

public interface UpdateAverageMovieRatingBoundary {

    void execute(Long movieId, int rating);
}
