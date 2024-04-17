package com.icritic.movies.core.usecase.category;

import com.icritic.movies.core.model.Category;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindAllCategoriesUseCase {

    private final FindAllCategoriesBoundary findAllCategoriesBoundary;

    private final FindAllCategoriesCachedBoundary findAllCategoriesCachedBoundary;

    private final SaveCategoriesToCacheBoundary saveCategoriesToCacheBoundary;

    public Page<Category> execute(Pageable pageable) {
        log.info("Finding all categories");

        Page<Category> cachedCategories = findAllCategoriesCachedBoundary.execute(pageable);

        if (nonNull(cachedCategories)) {
            return cachedCategories;
        }

        Page<Category> categories = findAllCategoriesBoundary.execute(pageable);
        saveCategoriesToCacheBoundary.execute(categories);

        return categories;
    }
}
