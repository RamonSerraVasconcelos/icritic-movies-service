package com.icritic.movies.core.usecase.fixture;

import com.icritic.movies.core.model.MovieRequestParams;

import java.time.LocalDate;
import java.util.List;

public class MovieRequestParamsFixture {

    public static MovieRequestParams load() {
        return MovieRequestParams.builder()
                .name("Drive")
                .synopsis("I Drive")
                .categories(List.of(1L))
                .directors(List.of(1L))
                .actors(List.of(1L))
                .countryId(1L)
                .releaseDate(LocalDate.now().minusYears(1))
                .build();
    }
}
