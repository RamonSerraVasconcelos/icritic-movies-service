package com.icritic.movies.core.usecase.movie.util;

import com.icritic.movies.core.model.Category;
import com.icritic.movies.core.usecase.category.FindCategoryByIdBoundary;
import com.icritic.movies.core.usecase.fixture.CategoryFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProcessMovieEntitiesUtilTest {

    @Mock
    private FindCategoryByIdBoundary findCategoryByIdBoundary;

    @Test
    void givenValidParams_whenProcessing_thenReturnList() {
        List<Long> ids = List.of(1L, 2L, 3L);

        when(findCategoryByIdBoundary.execute(anyLong())).thenReturn(Optional.of(CategoryFixture.load()));

        List<Category> categories = ProcessMovieEntitiesUtil.process(ids, findCategoryByIdBoundary::execute, "Category");

        assertThat(categories).isNotNull().hasSize(3);
        assertThat(categories.get(0).getId()).isEqualTo(ids.get(0));
    }
}