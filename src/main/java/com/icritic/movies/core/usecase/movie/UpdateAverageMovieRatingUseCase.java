package com.icritic.movies.core.usecase.movie;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateAverageMovieRatingUseCase {

    private final GetMovieAvarageRateBoundary getMovieAvarageRateBoundary;

    private final UpdateAverageMovieRatingBoundary updateAverageMovieRatingBoundary;

    public int execute(Long movieId) {
        int rating = getMovieAvarageRateBoundary.execute(movieId);

        updateAverageMovieRatingBoundary.execute(movieId, rating);

        return rating;
    }
}
