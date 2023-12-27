package com.icritic.movies.core.usecase.fixture;

import com.icritic.movies.core.model.Category;

public class CategoryFixture {

    public static Category load() {
        return Category.builder()
                .id(1L)
                .name("name")
                .description("description")
                .build();
    }
}
