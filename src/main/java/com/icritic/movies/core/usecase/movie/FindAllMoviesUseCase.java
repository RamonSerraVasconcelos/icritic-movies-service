package com.icritic.movies.core.usecase.movie;

import com.icritic.movies.core.model.Movie;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class FindAllMoviesUseCase {

    private final FindAllMoviesBoundary findAllMoviesBoundary;

    public List<Movie> execute() {
        try {
            log.info("Finding all movies");

            return findAllMoviesBoundary.execute();
        } catch (Exception e) {
            log.error("Error finding all movies", e);
            throw e;
        }
    }
}
