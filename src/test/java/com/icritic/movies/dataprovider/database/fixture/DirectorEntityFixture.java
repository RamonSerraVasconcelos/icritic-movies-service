package com.icritic.movies.dataprovider.database.fixture;

import com.icritic.movies.dataprovider.database.entity.DirectorEntity;

public class DirectorEntityFixture {

    public static DirectorEntity load() {
        return DirectorEntity.builder()
                .id(1L)
                .name("Steven Spielberg")
                .description("Certainly one of the directors in the world")
                .countryId(1L)
                .build();
    }
}
