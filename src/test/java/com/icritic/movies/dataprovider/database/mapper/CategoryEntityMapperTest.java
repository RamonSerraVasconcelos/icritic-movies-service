package com.icritic.movies.dataprovider.database.mapper;

import com.icritic.movies.core.model.Category;
import com.icritic.movies.core.usecase.fixture.CategoryFixture;
import com.icritic.movies.dataprovider.database.entity.CategoryEntity;
import com.icritic.movies.dataprovider.database.fixture.CategoryEntityFixture;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CategoryEntityMapperTest {

    @Test
    void givenCategory_ThenReturnCategoryEntity() {
        Category category = CategoryFixture.load();

        CategoryEntity categoryEntity = CategoryEntityMapper.INSTANCE.categoryToCategoryEntity(category);

        assertThat(categoryEntity).isNotNull().usingRecursiveComparison().isEqualTo(category);
    }

    @Test
    void givenCategoryEntity_ThenReturnCategory() {
        CategoryEntity categoryEntity = CategoryEntityFixture.load();

        Category category = CategoryEntityMapper.INSTANCE.categoryEntityToCategory(categoryEntity);

        assertThat(category).isNotNull().usingRecursiveComparison().isEqualTo(categoryEntity);
    }
}