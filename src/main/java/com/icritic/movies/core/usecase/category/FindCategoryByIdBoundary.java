package com.icritic.movies.core.usecase.category;

import com.icritic.movies.core.model.Category;

import java.util.Optional;

public interface FindCategoryByIdBoundary {

    Optional<Category> execute(Long id);
}
