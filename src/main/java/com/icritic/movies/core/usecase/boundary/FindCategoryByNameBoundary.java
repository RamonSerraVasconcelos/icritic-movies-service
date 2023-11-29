package com.icritic.movies.core.usecase.boundary;

import com.icritic.movies.core.model.Category;

import java.util.Optional;

public interface FindCategoryByNameBoundary {

    Optional<Category> execute(String name);
}
