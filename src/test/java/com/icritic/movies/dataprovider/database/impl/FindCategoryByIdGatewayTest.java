package com.icritic.movies.dataprovider.database.impl;

import com.icritic.movies.core.model.Category;
import com.icritic.movies.dataprovider.database.entity.CategoryEntity;
import com.icritic.movies.dataprovider.database.fixture.CategoryEntityFixture;
import com.icritic.movies.dataprovider.database.impl.category.FindCategoryByIdGateway;
import com.icritic.movies.dataprovider.database.repository.CategoryEntityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindCategoryByIdGatewayTest {

    @InjectMocks
    private FindCategoryByIdGateway findCategoryByIdGateway;

    @Mock
    private CategoryEntityRepository repository;

    @Test
    void givenValidId_whenCategoryIsFound_thenReturnCategory() {
        CategoryEntity categoryEntity = CategoryEntityFixture.load();
        when(repository.findById(1L)).thenReturn(Optional.of(categoryEntity));

        Optional<Category> returnedCategory = findCategoryByIdGateway.execute(1L);

        verify(repository).findById(1L);

        assertTrue(returnedCategory.isPresent());
        assertThat(returnedCategory.get()).usingRecursiveComparison().isEqualTo(categoryEntity);
    }
}