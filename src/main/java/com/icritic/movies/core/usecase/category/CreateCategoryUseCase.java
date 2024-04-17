package com.icritic.movies.core.usecase.category;

import com.icritic.movies.core.model.Category;
import com.icritic.movies.exception.ResourceConflictException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateCategoryUseCase {

    private final FindCategoryByNameBoundary findCategoryByNameBoundary;

    private final SaveCategoryBoundary saveCategoryBoundary;

    private final InvalidateCategoriesCacheBoundary invalidateCategoriesCacheBoundary;

    public Category execute(String name, String description) {
        try {
            log.info("Creating category {}", name);

            Optional<Category> categoryOptional = findCategoryByNameBoundary.execute(name);

            if (categoryOptional.isPresent()) {
                throw new ResourceConflictException("Category already exists");
            }

            Category categoryToSave = Category.builder()
                    .name(name)
                    .description(description)
                    .build();

            Category savedCategory = saveCategoryBoundary.execute(categoryToSave);

            invalidateCategoriesCacheBoundary.execute();

            return savedCategory;
        } catch (Exception e) {
            log.error("Error creating category: [{}]", name, e);
            throw e;
        }
    }
}
