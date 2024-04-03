package com.icritic.movies.dataprovider.database.impl.movie;

import com.icritic.movies.core.usecase.movie.GetMovieAvarageRateBoundary;
import com.icritic.movies.dataprovider.database.repository.MovieEntityRatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetMovieAverageRateGateway implements GetMovieAvarageRateBoundary {

    private final MovieEntityRatingRepository movieEntityRatingRepository;

    public int execute(Long movieId) {
        return movieEntityRatingRepository.getAverageRate(movieId);
    }
}
