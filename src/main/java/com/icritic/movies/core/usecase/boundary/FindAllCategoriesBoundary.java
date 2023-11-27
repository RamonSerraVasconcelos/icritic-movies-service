package com.icritic.movies.core.usecase.boundary;

import com.icritic.movies.core.model.Category;

import java.util.List;

public interface FindAllCategoriesBoundary {

    List<Category> execute();
}
