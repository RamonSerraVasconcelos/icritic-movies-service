package com.icritic.movies.dataprovider.caching.impl.movie;

import com.icritic.movies.core.usecase.movie.InvalidateMoviesCacheBoundary;
import com.icritic.movies.dataprovider.caching.InvalidatePaginationCache;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class InvalidateMoviesCacheGateway implements InvalidateMoviesCacheBoundary {

    private final InvalidatePaginationCache invalidatePaginationCache;

    public void execute() {
        log.info("Invalidating movies cache");

        try {
            invalidatePaginationCache.execute("movies");
        } catch (Exception e) {
            log.error("Error when invalidating movies cache", e);
        }
    }
}
