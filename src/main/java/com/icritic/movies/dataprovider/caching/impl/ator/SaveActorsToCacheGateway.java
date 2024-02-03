package com.icritic.movies.dataprovider.caching.impl.ator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icritic.movies.core.model.Actor;
import com.icritic.movies.core.usecase.actor.SaveActorsToCacheBoundary;
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
public class SaveActorsToCacheGateway implements SaveActorsToCacheBoundary {

    private final Jedis jedis;

    private final ObjectMapper objectMapper;

    public void execute(Page<Actor> actors) {
        String cacheKey = CachingUtils.buildPaginationCachekey("actors", actors.getNumber(), actors.getSize());

        log.info("Saving actors to redis cache with cacheKey: [{}]", cacheKey);

        try {
            String jsonList = objectMapper.writeValueAsString(actors.getContent());

            jedis.set(cacheKey, jsonList);
            jedis.pexpire(cacheKey, TimeUnit.MINUTES.toMillis(15));
        } catch (Exception e) {
            log.error("Error when saving actors list to redis cache with cacheKey: [{}]", cacheKey, e);
        }
    }
}
