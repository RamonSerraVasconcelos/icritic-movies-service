package com.icritic.movies.dataprovider.database.impl.movie;

import com.icritic.movies.core.usecase.movie.UpsertMovieRateBoundary;
import com.icritic.movies.dataprovider.database.repository.MovieEntityRatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpsertMovieRateGateway implements UpsertMovieRateBoundary {

    private final MovieEntityRatingRepository movieEntityRatingRepository;

    public void execute(Long movieId, Long userId, int rating) {
        movieEntityRatingRepository.upsert(movieId, userId, rating);
    }
}
