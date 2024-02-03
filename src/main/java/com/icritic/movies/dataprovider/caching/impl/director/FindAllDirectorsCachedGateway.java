package com.icritic.movies.dataprovider.caching.impl.director;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icritic.movies.core.model.Director;
import com.icritic.movies.core.usecase.director.FindAllDirectorsCachedBoundary;
import com.icritic.movies.dataprovider.util.CachingUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.util.List;

import static java.util.Objects.isNull;

@Component
@RequiredArgsConstructor
@Slf4j
public class FindAllDirectorsCachedGateway implements FindAllDirectorsCachedBoundary {

    private final Jedis jedis;

    private final ObjectMapper objectMapper;

    public Page<Director> execute(Pageable pageable) {
        String cacheKey = CachingUtils.buildPaginationCachekey("directors", pageable.getPageNumber(), pageable.getPageSize());

        log.info("Fetching directors from redis cache with cacheKey: [{{}]", cacheKey);

        try {
            String directorsJson = jedis.get(cacheKey);

            if (isNull(directorsJson) || directorsJson.isBlank()) {
                return null;
            }

            List<Director> directors = objectMapper.readValue(directorsJson, new TypeReference<>() {});

            return new PageImpl<>(directors, pageable, directors.size());
        } catch (Exception e) {
            log.error("Error when fetching directors from redis cache with cacheKey: [{}]", cacheKey, e);
            return null;
        }
    }
}
