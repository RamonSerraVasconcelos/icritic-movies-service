package com.icritic.movies.dataprovider.database.impl;

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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindAllCategoriesGatewayTest {

    @InjectMocks
    private FindAllCategoriesGateway findAllCategoriesGateway;

    @Mock
    private CategoryEntityRepository categoryEntityRepository;

    @Test
    void givenExecution_thenFind_andReturnAllCategories() {
        List<CategoryEntity> categoriesList = List.of(CategoryEntityFixture.load(), CategoryEntityFixture.load());

        when(categoryEntityRepository.findAllByOrderByIdAsc()).thenReturn(categoriesList);

        List<Category> categories = findAllCategoriesGateway.execute();

        assertThat(categories).isNotNull().isNotEmpty().usingRecursiveComparison().isEqualTo(categoriesList);
    }
}