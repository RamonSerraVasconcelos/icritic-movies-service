package com.icritic.movies.core.usecase.category;

import com.icritic.movies.core.model.Category;
import com.icritic.movies.core.usecase.fixture.CategoryFixture;
import com.icritic.movies.exception.ResourceConflictException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateCategoryUseCaseTest {

    @InjectMocks
    private CreateCategoryUseCase createCategoryUseCase;

    @Mock
    private FindCategoryByNameBoundary findCategoryByNameBoundary;

    @Mock
    private SaveCategoryBoundary saveCategoryBoundary;

    @Test
    void givenValidParameters_thenCreate_andReturnCategor() {
        Category category = CategoryFixture.load();

        when(findCategoryByNameBoundary.execute(anyString())).thenReturn(Optional.empty());
        when(saveCategoryBoundary.execute(any(Category.class))).thenReturn(category);

        Category savedCategory = createCategoryUseCase.execute("test", "testing");

        verify(findCategoryByNameBoundary).execute(anyString());
        verify(saveCategoryBoundary).execute(any(Category.class));

        assertNotNull(savedCategory);
        assertEquals("name", savedCategory.getName());
        assertEquals("description", savedCategory.getDescription());
    }

    @Test
    void givenValidParameters_whenCategoryAlreadyExists_thenThrowResourceConflictException() {
        Category category = CategoryFixture.load();
        when(findCategoryByNameBoundary.execute(anyString())).thenReturn(Optional.of(category));

        assertThrows(ResourceConflictException.class, () -> createCategoryUseCase.execute("test", "testing"));

        verify(findCategoryByNameBoundary).execute(anyString());
        verifyNoInteractions(saveCategoryBoundary);
    }
}