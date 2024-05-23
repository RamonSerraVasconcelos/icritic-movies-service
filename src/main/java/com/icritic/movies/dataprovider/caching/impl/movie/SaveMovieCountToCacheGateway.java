package com.icritic.movies.dataprovider.caching.impl.movie;

import com.icritic.movies.core.usecase.movie.SaveMovieCountToCacheBoundary;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
@Slf4j
public class SaveMovieCountToCacheGateway implements SaveMovieCountToCacheBoundary {

    private final Jedis jedis;

    public void execute(Long count) {
        String cacheKey = "movies-count";

        log.info("Saving movie count to redis cache with cacheKey: [{}]", cacheKey);

        try {
            jedis.set(cacheKey, String.valueOf(count));
            jedis.pexpire(cacheKey, TimeUnit.MINUTES.toMillis(15));
        } catch (Exception e) {
            log.error("Error when saving movie count to redis cache with cacheKey: [{}]", cacheKey, e);
        }
    }
}
