package com.icritic.movies.dataprovider.caching.impl.ator;

import com.icritic.movies.core.usecase.actor.InvalidateActorsCacheBoundary;
import com.icritic.movies.dataprovider.caching.InvalidatePaginationCache;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class InvalidateActorsCacheGateway implements InvalidateActorsCacheBoundary {

    private final InvalidatePaginationCache invalidatePaginationCache;

    public void execute() {
        log.info("Invalidating actors cache");

        try {
            invalidatePaginationCache.execute("actors");
        } catch (Exception e) {
            log.error("Error when invalidating actors cache", e);
        }
    }
}
