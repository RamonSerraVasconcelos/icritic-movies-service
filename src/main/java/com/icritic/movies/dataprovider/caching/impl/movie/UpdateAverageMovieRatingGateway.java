package com.icritic.movies.dataprovider.caching.impl.movie;

import com.icritic.movies.core.usecase.movie.UpdateAverageMovieRatingBoundary;
import com.icritic.movies.dataprovider.database.repository.MovieEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateAverageMovieRatingGateway implements UpdateAverageMovieRatingBoundary {

    private final MovieEntityRepository movieEntityRepository;

    public void execute(Long movieId, int rating) {
        movieEntityRepository.updateMovieRatingById(movieId, rating);
    }
}
