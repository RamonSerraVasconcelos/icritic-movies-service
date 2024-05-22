package com.icritic.movies.dataprovider.database.impl.movie;

import com.icritic.movies.core.model.Category;
import com.icritic.movies.core.usecase.movie.FindMovieCategoriesBoundary;
import com.icritic.movies.dataprovider.database.entity.CategoryEntity;
import com.icritic.movies.dataprovider.database.mapper.CategoryEntityMapper;
import com.icritic.movies.dataprovider.database.repository.CategoryEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FindMovieCategoriesGateway implements FindMovieCategoriesBoundary {

    private final CategoryEntityRepository categoryEntityRepository;

    public List<Category> execute(Long movieId) {
        CategoryEntityMapper mapper = CategoryEntityMapper.INSTANCE;

        List<CategoryEntity> categories = categoryEntityRepository.findByMovieId(movieId);

        return categories
                .stream()
                .map(mapper::categoryEntityToCategory)
                .collect(Collectors.toList());
    }
}
