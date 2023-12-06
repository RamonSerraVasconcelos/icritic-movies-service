package com.icritic.movies.dataprovider.database.impl.category;

import com.icritic.movies.core.model.Category;
import com.icritic.movies.core.usecase.boundary.category.FindCategoryByNameBoundary;
import com.icritic.movies.dataprovider.database.entity.CategoryEntity;
import com.icritic.movies.dataprovider.database.mapper.CategoryEntityMapper;
import com.icritic.movies.dataprovider.database.repository.CategoryEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FindCategoryByNameGateway implements FindCategoryByNameBoundary {

    private final CategoryEntityRepository repository;

    public Optional<Category> execute(String name) {
        CategoryEntity categoryEntity = repository.findByName(name);

        CategoryEntityMapper mapper = CategoryEntityMapper.INSTANCE;

        return Optional.ofNullable(categoryEntity).map(mapper::categoryEntityToCategory);
    }
}
