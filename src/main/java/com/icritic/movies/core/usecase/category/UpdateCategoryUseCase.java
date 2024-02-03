package com.icritic.movies.core.usecase.category;

import com.icritic.movies.core.model.Category;
import com.icritic.movies.exception.ResourceConflictException;
import com.icritic.movies.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class UpdateCategoryUseCase {

    private final FindCategoryByIdBoundary findCategoryByIdBoundary;

    private final FindCategoryByNameBoundary findCategoryByNameBoundary;

    private final SaveCategoryBoundary saveCategoryBoundary;

    private final InvalidateCategoriesCacheBoundary invalidateCategoriesCacheBoundary;

    public Category execute(Long id, String name, String description) {
        try {
            log.info("Updating category with id: [{}]", id);

            Optional<Category> categoryOptional = findCategoryByIdBoundary.execute(id);

            if (categoryOptional.isEmpty()) {
                throw new ResourceNotFoundException("Category not found");
            }

            Category categoryToUpdate = categoryOptional.get();

            Optional<Category> doesCategoryExist = findCategoryByNameBoundary.execute(name);
            if (doesCategoryExist.isPresent()) {
                throw new ResourceConflictException("Category already exists");
            }

            categoryToUpdate.setName(name);
            categoryToUpdate.setDescription(description);

            Category updatedCategory = saveCategoryBoundary.execute(categoryToUpdate);

            invalidateCategoriesCacheBoundary.execute();

            return updatedCategory;
        } catch (Exception e) {
            log.error("Error updating category with id: [{}]", id, e);
            throw e;
        }
    }
}
