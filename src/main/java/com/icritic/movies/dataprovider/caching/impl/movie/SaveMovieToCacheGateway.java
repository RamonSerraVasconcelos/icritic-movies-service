package com.icritic.movies.dataprovider.caching.impl.movie;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icritic.movies.core.model.Movie;
import com.icritic.movies.core.usecase.movie.SaveMovieToCacheBoundary;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
@Slf4j
public class SaveMovieToCacheGateway implements SaveMovieToCacheBoundary {

    private final Jedis jedis;

    private final ObjectMapper objectMapper;

    public void execute(Movie movie) {
        String cacheKey = "movie_" + movie.getId();

        log.info("Saving movie to redis cache with cacheKey: [{}]", cacheKey);

        try {
            String movieString = objectMapper.writeValueAsString(movie);

            jedis.set(cacheKey, movieString);
            jedis.pexpire(cacheKey, TimeUnit.MINUTES.toMillis(10));
        } catch (Exception e) {
            log.error("Error when saving movie to redis cache", e);
        }
    }
}
