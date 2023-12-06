package com.icritic.movies.core.usecase.boundary.category;

import com.icritic.movies.core.model.Category;

import java.util.Optional;

public interface FindCategoryByNameBoundary {

    Optional<Category> execute(String name);
}
