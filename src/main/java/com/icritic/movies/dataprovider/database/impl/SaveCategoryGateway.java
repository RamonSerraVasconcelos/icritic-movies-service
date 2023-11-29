package com.icritic.movies.dataprovider.database.impl;

import com.icritic.movies.core.model.Category;
import com.icritic.movies.core.usecase.boundary.SaveCategoryBoundary;
import com.icritic.movies.dataprovider.database.entity.CategoryEntity;
import com.icritic.movies.dataprovider.database.mapper.CategoryEntityMapper;
import com.icritic.movies.dataprovider.database.repository.CategoryEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SaveCategoryGateway implements SaveCategoryBoundary {

    private final CategoryEntityRepository repository;

    public Category execute(Category category) {
        CategoryEntityMapper mapper = CategoryEntityMapper.INSTANCE;

        CategoryEntity categoryEntity = mapper.categoryToCategoryEntity(category);

        return mapper.categoryEntityToCategory(repository.save(categoryEntity));
    }
}
