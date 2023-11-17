package com.icritic.movies.core.usecase.category;

import com.icritic.movies.core.model.Category;
import com.icritic.movies.core.model.enums.Role;
import com.icritic.movies.core.usecase.boundary.FindCategoryByNameBoundary;
import com.icritic.movies.core.usecase.boundary.SaveCategoryBoundary;
import com.icritic.movies.core.usecase.user.ValidateUserRoleUseCase;
import com.icritic.movies.exception.ResourceConflictException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreateCategoryUseCase {

    private final ValidateUserRoleUseCase validateUserRoleUseCase;

    private final FindCategoryByNameBoundary findCategoryByNameBoundary;

    private final SaveCategoryBoundary saveCategoryBoundary;

    public Category execute(String name, String description, String userRole) {
        try {
            log.info("Creating category {}", name);

            validateUserRoleUseCase.execute(List.of(Role.MODERATOR), userRole);

            Optional<Category> categoryOptional = findCategoryByNameBoundary.execute(name);

            if(categoryOptional.isPresent()) {
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
