package com.icritic.movies.dataprovider.database.impl;

import com.icritic.movies.core.model.Category;
import com.icritic.movies.core.usecase.boundary.FindCategoryByIdBoundary;
import com.icritic.movies.dataprovider.database.mapper.CategoryEntityMapper;
import com.icritic.movies.dataprovider.database.repository.CategoryEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FindCategoryByIdGateway implements FindCategoryByIdBoundary {

    private final CategoryEntityRepository repository;

    public Optional<Category> execute(Long id) {
        return repository.findById(id).map(CategoryEntityMapper.INSTANCE::categoryEntityToCategory);
    }
}
