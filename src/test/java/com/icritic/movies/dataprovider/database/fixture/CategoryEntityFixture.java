package com.icritic.movies.dataprovider.database.fixture;

import com.icritic.movies.dataprovider.database.entity.CategoryEntity;

public class CategoryEntityFixture {

    public static CategoryEntity load() {
        return CategoryEntity.builder()
                .id(1L)
                .name("action")
                .description("action movie")
                .build();
    }

}
