package com.icritic.movies.dataprovider.caching.impl.director;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icritic.movies.core.model.Director;
import com.icritic.movies.core.usecase.director.SaveDirectorsToCacheBoundary;
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
public class SaveDirectorsToCacheGateway implements SaveDirectorsToCacheBoundary {

    private final RedisTemplate<String, Object> redisTemplate;

    private final ObjectMapper objectMapper;

    public void execute(Page<Director> directors) {
        String cacheKey = CachingUtils.buildPaginationCachekey("directors", directors.getNumber(), directors.getSize());

        log.info("Saving directors to redis cache with cacheKey: [{}]", cacheKey);

        try {
            String jsonList = objectMapper.writeValueAsString(directors.getContent());

            redisTemplate.opsForValue().set(cacheKey, jsonList);
            redisTemplate.expire(cacheKey, 15, TimeUnit.MINUTES);
        } catch (Exception e) {
            log.error("Error when saving directors list to redis cache with cacheKey: [{}]", cacheKey, e);
        }
    }
}
