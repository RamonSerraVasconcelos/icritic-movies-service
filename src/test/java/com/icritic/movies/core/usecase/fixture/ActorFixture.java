package com.icritic.movies.core.usecase.fixture;

import com.icritic.movies.core.model.Actor;

public class ActorFixture {

    public static Actor load() {
        return Actor.builder()
                .id(1L)
                .name("Ryan Gosling")
                .description("Literally me")
                .country(CountryFixture.load())
                .build();
    }
}
