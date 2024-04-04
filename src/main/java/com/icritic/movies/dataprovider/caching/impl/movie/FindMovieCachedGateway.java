package com.icritic.movies.dataprovider.caching.impl.movie;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icritic.movies.core.model.Movie;
import com.icritic.movies.core.usecase.movie.FindMovieCachedBoundary;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import static java.util.Objects.isNull;

@Component
@RequiredArgsConstructor
@Slf4j
public class FindMovieCachedGateway implements FindMovieCachedBoundary {

    private final Jedis jedis;

    private final ObjectMapper objectMapper;

    public Movie execute(Long movieId) {
        String cacheKey = "movie_" + movieId;

        log.info("Fetching movie from redis cache with cacheKey: [{}]", cacheKey);

        try {
            String movieJson = jedis.get(cacheKey);

            if (isNull(movieJson) || movieJson.isBlank()) {
                return null;
            }

            return objectMapper.readValue(movieJson, new TypeReference<>() {});
        } catch (Exception e) {
            log.error("Error when fetching movie from redis cache with cacheKey: [{}]", cacheKey, e);
            return null;
        }
    }
}
