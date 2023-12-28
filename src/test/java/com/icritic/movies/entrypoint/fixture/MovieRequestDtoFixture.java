package com.icritic.movies.entrypoint.fixture;

import com.icritic.movies.entrypoint.dto.movie.MovieRequestDto;

import java.time.LocalDate;
import java.util.List;

public class MovieRequestDtoFixture {

    public static MovieRequestDto load() {
        return MovieRequestDto.builder()
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
