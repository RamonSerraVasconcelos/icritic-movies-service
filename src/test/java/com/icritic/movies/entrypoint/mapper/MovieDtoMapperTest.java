package com.icritic.movies.entrypoint.mapper;


import com.icritic.movies.core.model.Movie;
import com.icritic.movies.core.model.MovieRequestParams;
import com.icritic.movies.core.usecase.fixture.MovieFixture;
import com.icritic.movies.entrypoint.dto.movie.MovieRequestDto;
import com.icritic.movies.entrypoint.dto.movie.MovieResponseDto;
import com.icritic.movies.entrypoint.fixture.MovieRequestDtoFixture;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MovieDtoMapperTest {

    @Test
    void givenMovieRequestDto_thenReturnMovieRequestParams() {
        MovieRequestDto movieRequestDto = MovieRequestDtoFixture.load();

        MovieDtoMapper mapper = MovieDtoMapper.INSTANCE;

        MovieRequestParams movieRequestParams = mapper.movieRequestDtoToMovieRequestParams(movieRequestDto);

        assertThat(movieRequestParams).isNotNull().usingRecursiveComparison().ignoringExpectedNullFields().isEqualTo(movieRequestDto);
    }

    @Test
    void givenMovie_thenReturnMovieDtoResponse() {
        Movie movie = MovieFixture.load();

        MovieDtoMapper mapper = MovieDtoMapper.INSTANCE;

        MovieResponseDto movieResponseDto = mapper.movieToMovieResponseDto(movie);

        assertThat(movieResponseDto).isNotNull().usingRecursiveComparison().ignoringExpectedNullFields().isEqualTo(movie);
    }
}