package com.icritic.movies.dataprovider.database.impl;

import com.icritic.movies.core.model.Category;
import com.icritic.movies.core.usecase.fixture.CategoryFixture;
import com.icritic.movies.dataprovider.database.entity.CategoryEntity;
import com.icritic.movies.dataprovider.database.fixture.CategoryEntityFixture;
import com.icritic.movies.dataprovider.database.impl.category.SaveCategoryGateway;
import com.icritic.movies.dataprovider.database.repository.CategoryEntityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SaveCategoryGatewayTest {

    @InjectMocks
    private SaveCategoryGateway saveCategoryGateway;

    @Mock
    private CategoryEntityRepository repository;

    @Test
    void givenValidCategory_whenCategoryIsSaved_thenReturnSavedCategory() {
        Category category = CategoryFixture.load();
        CategoryEntity categoryEntity = CategoryEntityFixture.load();

        when(repository.save(any(CategoryEntity.class))).thenReturn(categoryEntity);

        Category returnedCategory = saveCategoryGateway.execute(category);

        verify(repository).save(any(CategoryEntity.class));

        assertThat(returnedCategory).usingRecursiveComparison().isEqualTo(categoryEntity);
    }
}