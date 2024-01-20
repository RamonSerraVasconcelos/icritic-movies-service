package com.icritic.movies.dataprovider.caching.impl.category;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icritic.movies.core.model.Category;
import com.icritic.movies.core.usecase.category.SaveCategoriesToCacheBoundary;
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
public class SaveCategoriesToCacheGateway implements SaveCategoriesToCacheBoundary {

    private final RedisTemplate<String, Object> redisTemplate;

    private final ObjectMapper objectMapper;

    public void execute(Page<Category> categories) {
        String cacheKey = CachingUtils.buildPaginationCachekey("categories", categories.getNumber(), categories.getSize());

        log.info("Saving categories to redis cache with cacheKey: [{}]", cacheKey);

        try {
            String jsonList = objectMapper.writeValueAsString(categories.getContent());

            redisTemplate.opsForValue().set(cacheKey, jsonList);
            redisTemplate.expire(cacheKey, 1, TimeUnit.HOURS);
        } catch (Exception e) {
            log.error("Error when saving categories list to redis cache with cacheKey: [{}]", cacheKey, e);
        }
    }
}
