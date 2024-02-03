package com.icritic.movies.dataprovider.caching.impl.director;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icritic.movies.core.model.Director;
import com.icritic.movies.core.usecase.director.SaveDirectorsToCacheBoundary;
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
public class SaveDirectorsToCacheGateway implements SaveDirectorsToCacheBoundary {

    private final Jedis jedis;

    private final ObjectMapper objectMapper;

    public void execute(Page<Director> directors) {
        String cacheKey = CachingUtils.buildPaginationCachekey("directors", directors.getNumber(), directors.getSize());

        log.info("Saving directors to redis cache with cacheKey: [{}]", cacheKey);

        try {
            String jsonList = objectMapper.writeValueAsString(directors.getContent());

            jedis.set(cacheKey, jsonList);
            jedis.pexpire(cacheKey, TimeUnit.MINUTES.toMillis(15));
        } catch (Exception e) {
            log.error("Error when saving directors list to redis cache with cacheKey: [{}]", cacheKey, e);
        }
    }
}
