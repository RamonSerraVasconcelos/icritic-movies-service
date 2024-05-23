package com.icritic.movies.dataprovider.database.impl.movie;

import com.icritic.movies.core.model.Category;
import com.icritic.movies.dataprovider.database.entity.CategoryEntity;
import com.icritic.movies.dataprovider.database.fixture.CategoryEntityFixture;
import com.icritic.movies.dataprovider.database.repository.CategoryEntityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindMovieCategoriesGatewayTest {

    @InjectMocks
    private FindMovieCategoriesGateway findMovieCategoriesGateway;

    @Mock
    private CategoryEntityRepository categoryEntityRepository;

    @Test
    void givenExecution_returnCategories() {
        List<CategoryEntity> categoriesEntities = List.of(CategoryEntityFixture.load(), CategoryEntityFixture.load());

        when(categoryEntityRepository.findByMovieId(1L)).thenReturn(categoriesEntities);

        List<Category> categories = findMovieCategoriesGateway.execute(1L);

        verify(categoryEntityRepository).findByMovieId(1L);
        assertThat(categories).usingRecursiveComparison().isEqualTo(categoriesEntities);
    }
}