package com.icritic.movies.dataprovider.caching.impl.category;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icritic.movies.core.model.Category;
import com.icritic.movies.core.usecase.category.SaveCategoriesToCacheBoundary;
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
public class SaveCategoriesToCacheGateway implements SaveCategoriesToCacheBoundary {

    private final Jedis jedis;

    private final ObjectMapper objectMapper;

    public void execute(Page<Category> categories) {
        String cacheKey = CachingUtils.buildPaginationCachekey("categories", categories.getNumber(), categories.getSize());

        log.info("Saving categories to redis cache with cacheKey: [{}]", cacheKey);

        try {
            String jsonList = objectMapper.writeValueAsString(categories.getContent());

            jedis.set(cacheKey, jsonList);
            jedis.pexpire(cacheKey, TimeUnit.HOURS.toMillis(1));
        } catch (Exception e) {
            log.error("Error when saving categories list to redis cache with cacheKey: [{}]", cacheKey, e);
        }
    }
}
