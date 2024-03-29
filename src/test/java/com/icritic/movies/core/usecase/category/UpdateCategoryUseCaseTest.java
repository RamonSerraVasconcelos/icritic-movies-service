package com.icritic.movies.core.usecase.category;

import com.icritic.movies.core.model.Category;
import com.icritic.movies.core.usecase.fixture.CategoryFixture;
import com.icritic.movies.exception.ResourceConflictException;
import com.icritic.movies.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
class UpdateCategoryUseCaseTest {

    @InjectMocks
    private UpdateCategoryUseCase updateCategoryUseCase;

    @Mock
    private FindCategoryByIdBoundary findCategoryByIdBoundary;

    @Mock
    private FindCategoryByNameBoundary findCategoryByNameBoundary;

    @Mock
    private SaveCategoryBoundary saveCategoryBoundary;

    @Mock
    private InvalidateCategoriesCacheBoundary invalidateCategoriesCacheBoundary;

    @Test
    void givenValidParameters_whenCategoryIsFound_thenUpdateCategory() {
        Category category = CategoryFixture.load();

        when(findCategoryByIdBoundary.execute(2L)).thenReturn(Optional.of(category));
        when(findCategoryByNameBoundary.execute(category.getName())).thenReturn(Optional.empty());
        when(saveCategoryBoundary.execute(any(Category.class))).thenReturn(category);

        Category updatedCategory = updateCategoryUseCase.execute(2L, category.getName(), category.getDescription());

        verify(findCategoryByIdBoundary).execute(2L);
        verify(findCategoryByNameBoundary).execute(category.getName());
        verify(saveCategoryBoundary).execute(any(Category.class));
        verify(invalidateCategoriesCacheBoundary).execute();

        assertNotNull(updatedCategory);
    }

    @Test
    void givenValidParameters_whenCategoryIsNotFound_thenThrowResourceNotFoundException() {
        when(findCategoryByIdBoundary.execute(2L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> updateCategoryUseCase.execute(2L, "name", "description"));

        verify(findCategoryByIdBoundary).execute(2L);
        verifyNoInteractions(findCategoryByNameBoundary);
        verifyNoInteractions(saveCategoryBoundary);
        verifyNoInteractions(invalidateCategoriesCacheBoundary);
    }

    @Test
    void givenValidParameters_whenCategoryNameAlreadyExists_thenThrowResourceConflictException() {
        Category category = CategoryFixture.load();

        when(findCategoryByIdBoundary.execute(2L)).thenReturn(Optional.of(category));
        when(findCategoryByNameBoundary.execute(category.getName())).thenReturn(Optional.of(category));

        assertThrows(ResourceConflictException.class, () -> updateCategoryUseCase.execute(2L, category.getName(), category.getDescription()));

        verify(findCategoryByIdBoundary).execute(2L);
        verify(findCategoryByNameBoundary).execute(category.getName());
        verifyNoInteractions(saveCategoryBoundary);
        verifyNoInteractions(invalidateCategoriesCacheBoundary);
    }
}