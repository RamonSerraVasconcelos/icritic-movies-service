package com.icritic.movies.core.usecase.movie;

import com.icritic.movies.core.model.Movie;
import com.icritic.movies.core.model.MovieFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindAllMoviesUseCase {

    private final FindAllMoviesBoundary findAllMoviesBoundary;

    private final FindAllMoviesCachedBoundary findAllMoviesCachedBoundary;

    private final CountMoviesUseCase countMoviesUseCase;

    private final SaveMoviesToCacheBoundary saveMoviesToCacheBoundary;

    private boolean shouldCacheMovies;

    public Page<Movie> execute(MovieFilter movieFilter) {
        try {
            log.info("Finding all movies with params: {}", movieFilter.toString());

            List<Movie> movies = getMovies(movieFilter);

            Long totalMoviesCount = countMoviesUseCase.execute(movieFilter);
            Page<Movie> pageableMovies = new PageImpl<>(movies, movieFilter.getPageable(), totalMoviesCount);

            if (movieFilter.isCacheable() && shouldCacheMovies) {
                saveMoviesToCacheBoundary.execute(pageableMovies);
            }

            return pageableMovies;
        } catch (Exception e) {
            log.error("Error finding all movies", e);
            throw e;
        }
    }

    private List<Movie> getMovies(MovieFilter movieFilter) {
        List<Movie> movies = new ArrayList<>();

        if (movieFilter.isCacheable()) {
            movies = findAllMoviesCachedBoundary.execute(movieFilter.getPageable());
            shouldCacheMovies = false;
        }

        if (movies.isEmpty()) {
            log.info("Searching movies on database");

            movies = findAllMoviesBoundary.execute(movieFilter);
            shouldCacheMovies = true;
        }

        return movies;
    }

    public void setMovieCategories(List<Movie> movies) {

    }
}
