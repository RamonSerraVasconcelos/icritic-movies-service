package com.icritic.movies.core.usecase.category;

import com.icritic.movies.core.model.Category;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class FindAllCategoriesUseCase {

    private final FindAllCategoriesBoundary findAllCategoriesBoundary;

    public List<Category> execute() {
        log.info("Finding all categories");
        return findAllCategoriesBoundary.execute();
    }
}
