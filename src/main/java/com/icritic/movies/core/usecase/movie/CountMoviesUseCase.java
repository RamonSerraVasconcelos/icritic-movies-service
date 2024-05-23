package com.icritic.movies.core.usecase.movie;

import com.icritic.movies.core.model.MovieFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
@Slf4j
public class CountMoviesUseCase {

    private final FindMovieCountCachedBoundary findMovieCountCachedBoundary;

    private final CountMoviesBoundary countMoviesBoundary;

    private final SaveMovieCountToCacheBoundary saveMovieCountToCacheBoundary;

    public Long execute(MovieFilter movieFilter) {
        try {
            log.info("Counting all movies");

            if (movieFilter.isCacheable()) {
                Long cachedCount = findMovieCountCachedBoundary.execute();

                if (nonNull(cachedCount)) {
                    return cachedCount;
                }
            }

            Long count = countMoviesBoundary.execute(movieFilter);

            if (movieFilter.isCacheable()) saveMovieCountToCacheBoundary.execute(count);

            return count;
        } catch (Exception e) {
            log.error("Error counting movies", e);
            throw e;
        }
    }
}
