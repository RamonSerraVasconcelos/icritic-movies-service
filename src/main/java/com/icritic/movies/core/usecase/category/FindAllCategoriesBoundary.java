package com.icritic.movies.core.usecase.category;

import com.icritic.movies.core.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FindAllCategoriesBoundary {

    Page<Category> execute(Pageable pageable);
}
