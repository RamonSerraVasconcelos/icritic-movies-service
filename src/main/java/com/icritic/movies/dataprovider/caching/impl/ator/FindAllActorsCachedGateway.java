package com.icritic.movies.dataprovider.caching.impl.ator;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icritic.movies.core.model.Actor;
import com.icritic.movies.core.usecase.actor.FindAllActorsCachedBoundary;
import com.icritic.movies.dataprovider.util.CachingUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Objects.isNull;

@Component
@RequiredArgsConstructor
@Slf4j
public class FindAllActorsCachedGateway implements FindAllActorsCachedBoundary {

    private final RedisTemplate<String, Object> redisTemplate;

    private final ObjectMapper objectMapper;

    public Page<Actor> execute(Pageable pageable) {
        String cacheKey = CachingUtils.buildPaginationCachekey("actors", pageable.getPageNumber(), pageable.getPageSize());

        log.info("Getting actors from redis cache with cacheKey: [{}]", cacheKey);

        try {
            String actorsJson = (String) redisTemplate.opsForValue().get(cacheKey);

            if (isNull(actorsJson) || actorsJson.isBlank()) {
                return null;
            }

            List<Actor> actors = objectMapper.readValue(actorsJson, new TypeReference<>() {});

            return new PageImpl<>(actors, pageable, actors.size());
        } catch (Exception e) {
            log.error("Error when getting actors list from redis cache with cacheKey: [{}]", cacheKey, e);
            return null;
        }
    }
}
