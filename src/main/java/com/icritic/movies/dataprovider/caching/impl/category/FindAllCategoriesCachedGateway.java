package com.icritic.movies.dataprovider.caching.impl.category;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icritic.movies.core.model.Category;
import com.icritic.movies.core.usecase.category.FindAllCategoriesCachedBoundary;
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
public class FindAllCategoriesCachedGateway implements FindAllCategoriesCachedBoundary {

    private final Jedis jedis;

    private final ObjectMapper objectMapper;

    public Page<Category> execute(Pageable pageable) {
        String cacheKey = CachingUtils.buildPaginationCachekey("categories", pageable.getPageNumber(), pageable.getPageSize());

        log.info("Fetching categories from redis cache with cacheKey: [{{}]", cacheKey);

        try {
            String categoriesJson = jedis.get(cacheKey);

            if (isNull(categoriesJson) || categoriesJson.isBlank()) {
                return null;
            }

            List<Category> categories = objectMapper.readValue(categoriesJson, new TypeReference<>() {});

            return new PageImpl<>(categories, pageable, categories.size());
        } catch (Exception e) {
            log.error("Error when fetching categories from redis cache with cacheKey: [{}]", cacheKey, e);
            return null;
        }
    }
}
