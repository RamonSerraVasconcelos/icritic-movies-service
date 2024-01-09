package com.icritic.movies.core.usecase.movie;

import com.icritic.movies.core.model.Movie;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class FindAllMoviesUseCase {

    private final FindAllMoviesBoundary findAllMoviesBoundary;

    public Page<Movie> execute(Pageable pageable) {
        try {
            log.info("Finding all movies");

            return findAllMoviesBoundary.execute(pageable);
        } catch (Exception e) {
            log.error("Error finding all movies", e);
            throw e;
        }
    }
}
