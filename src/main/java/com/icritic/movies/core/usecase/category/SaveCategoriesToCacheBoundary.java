package com.icritic.movies.core.usecase.category;

import com.icritic.movies.core.model.Category;
import org.springframework.data.domain.Page;

public interface SaveCategoriesToCacheBoundary {

    void execute(Page<Category> categories);
}
