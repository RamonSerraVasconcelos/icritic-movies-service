package com.icritic.movies.dataprovider.caching.impl.category;

import com.icritic.movies.core.usecase.category.InvalidateCategoryCacheBoundary;
import com.icritic.movies.dataprovider.caching.InvalidatePaginationCache;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class InvalidateCategoryCacheGateway implements InvalidateCategoryCacheBoundary {

    private final InvalidatePaginationCache invalidatePaginationCache;

    public void execute() {
        try {
            log.info("Invalidating categories paginated cache");

            invalidatePaginationCache.execute("categories");
        } catch (Exception e) {
            log.error("Error invalidating category cache", e);
        }
    }
}
