package com.icritic.movies.entrypoint.mapper;

import com.icritic.movies.core.model.Category;
import com.icritic.movies.core.usecase.fixture.CategoryFixture;
import com.icritic.movies.entrypoint.dto.category.CategoryResponseDto;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CategoryDtoMapperTest {

    @Test
    void givenCategory_ThenReturnCategoryResponseDto() {
        Category category = CategoryFixture.load();

        CategoryResponseDto categoryResponseDto = CategoryDtoMapper.INSTANCE.categoryToCategoryResponsetDto(category);

        assertThat(categoryResponseDto).isNotNull().usingRecursiveComparison().isEqualTo(category);
    }
}