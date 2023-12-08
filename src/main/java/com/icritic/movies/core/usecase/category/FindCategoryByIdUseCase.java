package com.icritic.movies.core.usecase.category;

import com.icritic.movies.core.model.Category;
import com.icritic.movies.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class FindCategoryByIdUseCase {

    private final FindCategoryByIdBoundary findCategoryByIdBoundary;

    public Category execute(Long id) {
        log.info("Finding category with id: [{}]", id);

        Optional<Category> optionalCategory = findCategoryByIdBoundary.execute(id);

        if(optionalCategory.isEmpty()) {
            throw new ResourceNotFoundException("Category not found");
        }

        return optionalCategory.get();
    }
}
