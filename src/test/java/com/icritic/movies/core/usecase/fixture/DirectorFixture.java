package com.icritic.movies.core.usecase.fixture;

import com.icritic.movies.core.model.Director;

public class DirectorFixture {

    public static Director load() {
        return Director.builder()
                .id(1L)
                .name("Steven Spielberg")
                .description("Certainly one of the directors in the world")
                .country(CountryFixture.load())
                .build();
    }
}
