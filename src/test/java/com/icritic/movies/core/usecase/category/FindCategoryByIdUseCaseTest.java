package com.icritic.movies.core.usecase.category;

import com.icritic.movies.core.model.Category;
import com.icritic.movies.core.usecase.boundary.category.FindCategoryByIdBoundary;
import com.icritic.movies.core.usecase.fixture.CategoryFixture;
import com.icritic.movies.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindCategoryByIdUseCaseTest {

    @InjectMocks
    private FindCategoryByIdUseCase findCategoryByIdUseCase;

    @Mock
    private FindCategoryByIdBoundary findCategoryByIdBoundary;

    @Test
    void givenValidId_whenFindById_thenReturnCategory() {
        Category category = CategoryFixture.load();

        when(findCategoryByIdBoundary.execute(1L)).thenReturn(Optional.of(category));

        Category returnedCategory = findCategoryByIdUseCase.execute(1L);

        assertThat(returnedCategory).isNotNull();
        assertThat(returnedCategory).usingRecursiveComparison().isEqualTo(category);
    }

    @Test
    void givenValidParameters_whenCategoryIsNotFound_thenThrowResourceNotFoundException() {
        when(findCategoryByIdBoundary.execute(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> findCategoryByIdUseCase.execute(1L));
    }
}