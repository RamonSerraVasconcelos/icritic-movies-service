package com.icritic.movies.core.usecase.category;

import com.icritic.movies.core.model.Category;
import com.icritic.movies.core.usecase.boundary.FindAllCategoriesBoundary;
import com.icritic.movies.core.usecase.fixture.CategoryFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindAllCategoriesUseCaseTest {

    @InjectMocks
    private FindAllCategoriesUseCase findAllCategoriesUseCase;

    @Mock
    private FindAllCategoriesBoundary findAllCategoriesBoundary;

    @Test
    void givenExecution_thenReturnAllCategories() {
        List<Category> categoriesList = List.of(CategoryFixture.load(), CategoryFixture.load());

        when(findAllCategoriesBoundary.execute()).thenReturn(categoriesList);

        List<Category> categories = findAllCategoriesUseCase.execute();

        assertThat(categories).isNotNull().isNotEmpty().usingRecursiveComparison().isEqualTo(categoriesList);
    }
}