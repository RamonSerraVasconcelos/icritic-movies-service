package com.icritic.movies.dataprovider.caching.impl.movie;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icritic.movies.core.model.Movie;
import com.icritic.movies.core.usecase.movie.SaveMoviesToCacheBoundary;
import com.icritic.movies.dataprovider.util.CachingUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
@Slf4j
public class SaveMoviesToCacheGateway implements SaveMoviesToCacheBoundary {

    private final Jedis jedis;

    private final ObjectMapper objectMapper;

    public void execute(Page<Movie> movies) {
        String cacheKey = CachingUtils.buildPaginationCachekey("movies", movies.getNumber(), movies.getSize());

        log.info("Saving movies to redis cache with cacheKey: [{}]", cacheKey);

        try {
            String jsonList = objectMapper.writeValueAsString(movies.getContent());

            jedis.set(cacheKey, jsonList);
            jedis.pexpire(cacheKey, TimeUnit.MINUTES.toMillis(10));
        } catch (Exception e) {
            log.error("Error when saving movies list to redis cache with cacheKey: [{}]", cacheKey, e);
        }
    }
}
