package com.icritic.movies.dataprovider.caching.impl.director;

import com.icritic.movies.core.usecase.director.InvalidateDirectorsCacheBoundary;
import com.icritic.movies.dataprovider.caching.InvalidatePaginationCache;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class InvalidateDirectorsCacheGateway implements InvalidateDirectorsCacheBoundary {

    private final InvalidatePaginationCache invalidatePaginationCache;

    public void execute() {
        log.info("Invalidating directors cache");

        try {
            invalidatePaginationCache.execute("directors");
        } catch (Exception e) {
            log.error("Error when invalidating directors cache", e);
        }
    }
}
