package com.icritic.movies.core.usecase.category;

import com.icritic.movies.core.model.Category;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class FindAllCategoriesUseCase {

    private final FindAllCategoriesBoundary findAllCategoriesBoundary;

    public Page<Category> execute(Pageable pageable) {
        log.info("Finding all categories");
        return findAllCategoriesBoundary.execute(pageable);
    }
}
