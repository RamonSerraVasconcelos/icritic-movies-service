package com.icritic.movies.dataprovider.caching.impl.movie;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icritic.movies.core.model.Movie;
import com.icritic.movies.core.usecase.movie.FindAllMoviesCachedBoundary;
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
public class FindAllMoviesCachedGateway implements FindAllMoviesCachedBoundary {

    private final Jedis jedis;

    private final ObjectMapper objectMapper;

    public Page<Movie> execute(Pageable pageable) {
        String cacheKey = CachingUtils.buildPaginationCachekey("movies", pageable.getPageNumber(), pageable.getPageSize());

        log.info("Fetching movies from redis cache with cacheKey: [{{}]", cacheKey);

        try {
            String moviesJson = jedis.get(cacheKey);

            if (isNull(moviesJson) || moviesJson.isBlank()) {
                return null;
            }

            List<Movie> movies = objectMapper.readValue(moviesJson, new TypeReference<>() {
            });

            return new PageImpl<>(movies, pageable, movies.size());
        } catch (Exception e) {
            log.error("Error when fetching movies from redis cache with cacheKey: [{}]", cacheKey, e);
            return null;
        }
    }
}
