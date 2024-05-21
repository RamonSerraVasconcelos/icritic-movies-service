package com.icritic.movies.core.usecase.movie;

import com.icritic.movies.core.model.Movie;
import com.icritic.movies.core.model.MovieFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindAllMoviesUseCase {

    private final FindAllMoviesBoundary findAllMoviesBoundary;

    private final FindAllMoviesCachedBoundary findAllMoviesCachedBoundary;

    private final CountMoviesBoundary countMoviesBoundary;

    private final SaveMoviesToCacheBoundary saveMoviesToCacheBoundary;

    public Page<Movie> execute(MovieFilter movieFilter) {
        try {
            log.info("Finding all movies with params: {}", movieFilter.toString());

            if (movieFilter.isCacheable()) {
                Page<Movie> cachedMovies = findAllMoviesCachedBoundary.execute(movieFilter.getPageable());

                if (nonNull(cachedMovies)) {
                    return cachedMovies;
                }
            }

            List<Movie> movies = findAllMoviesBoundary.execute(movieFilter);
            int totalMoviesCount = countMoviesBoundary.execute();

            Page<Movie> pageableMovies = new PageImpl<>(movies, movieFilter.getPageable(), totalMoviesCount);

            if (movieFilter.isCacheable()) saveMoviesToCacheBoundary.execute(pageableMovies);

            return pageableMovies;
        } catch (Exception e) {
            log.error("Error finding all movies", e);
            throw e;
        }
    }
}
