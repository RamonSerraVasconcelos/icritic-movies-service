package com.icritic.movies.core.usecase.fixture;

import com.icritic.movies.core.model.Country;

public class CountryFixture {

    public static Country load() {
        return Country.builder()
                .id(1L)
                .build();
    }
}
