package com.icritic.movies.core.usecase.movie;

import com.icritic.movies.core.model.Movie;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import static java.util.Objects.nonNull;

@Component
@RequiredArgsConstructor
@Slf4j
public class FindAllMoviesUseCase {

    private final FindAllMoviesBoundary findAllMoviesBoundary;

    private final FindAllMoviesCachedBoundary findAllMoviesCachedBoundary;

    private final SaveMoviesToCacheBoundary saveMoviesToCacheBoundary;

    public Page<Movie> execute(Pageable pageable) {
        try {
            log.info("Finding all movies");

            Page<Movie> cachedMovies = findAllMoviesCachedBoundary.execute(pageable);

            if (nonNull(cachedMovies)) {
                return cachedMovies;
            }

            Page<Movie> movies = findAllMoviesBoundary.execute(pageable);
            saveMoviesToCacheBoundary.execute(findAllMoviesBoundary.execute(pageable));

            return movies;
        } catch (Exception e) {
            log.error("Error finding all movies", e);
            throw e;
        }
    }
}
