package com.icritic.movies.core.usecase.category;

import com.icritic.movies.core.model.Category;
import com.icritic.movies.exception.ResourceConflictException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreateCategoryUseCase {

    private final FindCategoryByNameBoundary findCategoryByNameBoundary;

    private final SaveCategoryBoundary saveCategoryBoundary;

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

            return saveCategoryBoundary.execute(categoryToSave);
        } catch (Exception e) {
            log.error("Error creating category: [{}]", name, e);
            throw e;
        }
    }
}
