package com.icritic.movies.dataprovider.database.fixture;

import com.icritic.movies.dataprovider.database.entity.MovieEntity;

import java.time.LocalDate;
import java.util.List;

public class MovieEntityFixture {

    public static MovieEntity load() {
        return MovieEntity.builder()
                .id(1L)
                .name("Drive")
                .synopsis("I Drive")
                .categories(List.of(CategoryEntityFixture.load(), CategoryEntityFixture.load()))
                .directors(List.of(DirectorEntityFixture.load(), DirectorEntityFixture.load()))
                .actors(List.of(ActorEntityFixture.load(), ActorEntityFixture.load()))
                .countryId(1L)
                .releaseDate(LocalDate.now().minusYears(1))
                .build();
    }
}
