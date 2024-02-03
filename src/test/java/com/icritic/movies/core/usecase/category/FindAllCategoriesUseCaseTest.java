package com.icritic.movies.core.usecase.category;

import com.icritic.movies.core.model.Category;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindAllCategoriesUseCaseTest {

    @InjectMocks
    private FindAllCategoriesUseCase findAllCategoriesUseCase;

    @Mock
    private FindAllCategoriesBoundary findAllCategoriesBoundary;

    @Mock
    private FindAllCategoriesCachedBoundary findAllCategoriesCachedBoundary;

    @Mock
    private SaveCategoriesToCacheBoundary saveCategoriesToCacheBoundary;

    @Mock
    private Pageable pageable;

    @Mock
    private Page<Category> pageableCategory;

    @Test
    void givenExecution_whenCategoriesAreNotOnCache_thenReturnAllCategoriesByDatabase() {
        when(findAllCategoriesCachedBoundary.execute(pageable)).thenReturn(null);
        when(findAllCategoriesBoundary.execute(pageable)).thenReturn(pageableCategory);

        Page<Category> categories = findAllCategoriesUseCase.execute(pageable);

        verify(findAllCategoriesCachedBoundary).execute(pageable);
        verify(findAllCategoriesBoundary).execute(pageable);
        verify(saveCategoriesToCacheBoundary).execute(pageableCategory);

        assertThat(categories).isNotNull();
    }

    @Test
    void givenExecution_whenCategoriesAreOnCache_thenReturnAllCategoriesFromCache() {
        when(findAllCategoriesCachedBoundary.execute(pageable)).thenReturn(pageableCategory);

        Page<Category> categories = findAllCategoriesUseCase.execute(pageable);

        verify(findAllCategoriesCachedBoundary).execute(pageable);
        verifyNoInteractions(findAllCategoriesBoundary);
        verifyNoInteractions(saveCategoriesToCacheBoundary);
        assertThat(categories).isNotNull();
    }
}