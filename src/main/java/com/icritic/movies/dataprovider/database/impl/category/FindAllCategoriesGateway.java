package com.icritic.movies.dataprovider.database.impl.category;

import com.icritic.movies.core.model.Category;
import com.icritic.movies.core.usecase.category.FindAllCategoriesBoundary;
import com.icritic.movies.dataprovider.database.entity.CategoryEntity;
import com.icritic.movies.dataprovider.database.mapper.CategoryEntityMapper;
import com.icritic.movies.dataprovider.database.repository.CategoryEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FindAllCategoriesGateway implements FindAllCategoriesBoundary {

    private final CategoryEntityRepository categoryRepository;

    public Page<Category> execute(Pageable pageable) {
        CategoryEntityMapper mapper = CategoryEntityMapper.INSTANCE;

        Page<CategoryEntity> pageableCategories = categoryRepository.findAllByOrderByIdAsc(pageable);

        List<Category> categories = pageableCategories.getContent()
                .stream()
                .map(mapper::categoryEntityToCategory)
                .collect(Collectors.toList());

        return new PageImpl<>(categories, pageable, pageableCategories.getTotalElements());
    }
}
