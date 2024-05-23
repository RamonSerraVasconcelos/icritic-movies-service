package com.icritic.movies.dataprovider.caching.impl.movie;

import com.icritic.movies.core.usecase.movie.FindMovieCountCachedBoundary;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import static java.util.Objects.nonNull;

@Component
@RequiredArgsConstructor
@Slf4j
public class FindMovieCountCachedGateway implements FindMovieCountCachedBoundary {

    private final Jedis jedis;

    public Long execute() {
        String cacheKey = "movies-count";

        log.info("Finding movie count from redis cache with cacheKey: [{}]", cacheKey);

        try {
            String countValue = jedis.get(cacheKey);

            return nonNull(countValue) ? Long.parseLong(countValue) : null;
        } catch (Exception e) {
            log.error("Error when finding movie count from redis cache with cacheKey: [{}]", cacheKey, e);
            return null;
        }
    }
}
