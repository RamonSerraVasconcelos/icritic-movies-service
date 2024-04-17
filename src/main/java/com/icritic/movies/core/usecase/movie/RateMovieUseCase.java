package com.icritic.movies.core.usecase.movie;

import com.icritic.movies.core.model.Movie;
import com.icritic.movies.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class RateMovieUseCase {

    private final FindMovieByIdBoundary findMovieByIdBoundary;

    private final UpsertMovieRateBoundary upsertMovieRateBoundary;

    public void execute(Long movieId, Long userId, int rating) {
        try {
            log.info("Rating movie with id: [{}] and userId: [{}]", movieId, userId);

            Optional<Movie> optionalMovie = findMovieByIdBoundary.execute(movieId);

            if (optionalMovie.isEmpty()) {
                throw new ResourceNotFoundException("Movie not found");
            }

            upsertMovieRateBoundary.execute(movieId, userId, rating);
        } catch (Exception e) {
            log.error("Error inserting rating for movie with id: [{}]", movieId, e);
            throw e;
        }
    }
}
