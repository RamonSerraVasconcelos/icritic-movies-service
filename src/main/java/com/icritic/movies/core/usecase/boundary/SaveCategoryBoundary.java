package com.icritic.movies.core.usecase.boundary;

import com.icritic.movies.core.model.Category;

public interface SaveCategoryBoundary {

    Category execute(Category category);
}
