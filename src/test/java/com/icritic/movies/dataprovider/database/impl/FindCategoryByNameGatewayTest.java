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

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindCategoryByNameGatewayTest {

    @InjectMocks
    private FindCategoryByNameGateway findCategoryByNameGateway;

    @Mock
    private CategoryEntityRepository repository;

    @Test
    void givenValidName_whenCategoryIsFound_thenReturnCategory() {
        CategoryEntity categoryEntity = CategoryEntityFixture.load();

        when(repository.findByName("Action")).thenReturn(categoryEntity);

        Optional<Category> returnedCategory = findCategoryByNameGateway.execute("Action");

        verify(repository).findByName("Action");

        assertTrue(returnedCategory.isPresent());
        assertThat(returnedCategory.get()).usingRecursiveComparison().isEqualTo(categoryEntity);
    }
}