package com.icritic.movies.dataprovider.caching.impl.movie;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icritic.movies.core.model.Movie;
import com.icritic.movies.core.usecase.movie.SaveMoviesToCacheBoundary;
import com.icritic.movies.dataprovider.util.CachingUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
@Slf4j
public class SaveMoviesToCacheGateway implements SaveMoviesToCacheBoundary {

    private final RedisTemplate<String, Object> redisTemplate;

    private final ObjectMapper objectMapper;

    public void execute(Page<Movie> movies) {
        String cacheKey = CachingUtils.buildPaginationCachekey("movies", movies.getNumber(), movies.getSize());

        log.info("Saving movies to redis cache with cacheKey: [{}]", cacheKey);

        try {
            String jsonList = objectMapper.writeValueAsString(movies.getContent());

            redisTemplate.opsForValue().set(cacheKey, jsonList);
            redisTemplate.expire(cacheKey, 15, TimeUnit.MINUTES);
        } catch (Exception e) {
            log.error("Error when saving movies list to redis cache with cacheKey: [{}]", cacheKey, e);
        }
    }
}
