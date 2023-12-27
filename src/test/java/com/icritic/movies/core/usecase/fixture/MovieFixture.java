package com.icritic.movies.core.usecase.fixture;

import com.icritic.movies.core.model.Movie;

import java.time.LocalDate;
import java.util.List;

public class MovieFixture {

    public static Movie load() {
        return Movie.builder()
                .name("Drive")
                .synopsis("I Drive")
                .categories(List.of(CategoryFixture.load()))
                .directors(List.of(DirectorFixture.load()))
                .actors(List.of(ActorFixture.load()))
                .country(CountryFixture.load())
                .rating(0L)
                .releaseDate(LocalDate.now().minusYears(1))
                .build();
    }
}
