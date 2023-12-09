package com.icritic.movies.dataprovider.database.fixture;

import com.icritic.movies.dataprovider.database.entity.ActorEntity;

public class ActorEntityFixture {

    public static ActorEntity load() {
        return ActorEntity.builder()
                .id(1L)
                .name("Ryan Gosling")
                .description("Literally me")
                .countryId(1L)
                .build();
    }
}
