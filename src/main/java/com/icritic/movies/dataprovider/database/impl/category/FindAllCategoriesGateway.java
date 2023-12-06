package com.icritic.movies.dataprovider.database.impl.category;

import com.icritic.movies.core.model.Category;
import com.icritic.movies.core.usecase.boundary.category.FindAllCategoriesBoundary;
import com.icritic.movies.dataprovider.database.mapper.CategoryEntityMapper;
import com.icritic.movies.dataprovider.database.repository.CategoryEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FindAllCategoriesGateway implements FindAllCategoriesBoundary {

    private final CategoryEntityRepository categoryRepository;

    public List<Category> execute() {
        CategoryEntityMapper mapper = CategoryEntityMapper.INSTANCE;

        return categoryRepository.findAllByOrderByIdAsc().stream().map(mapper::categoryEntityToCategory).collect(Collectors.toList());
    }
}
