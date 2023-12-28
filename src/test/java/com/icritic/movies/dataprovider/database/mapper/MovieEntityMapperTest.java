package com.icritic.movies.dataprovider.database.mapper;

import com.icritic.movies.core.model.Movie;
import com.icritic.movies.core.usecase.fixture.MovieFixture;
import com.icritic.movies.dataprovider.database.entity.MovieEntity;
import com.icritic.movies.dataprovider.database.fixture.MovieEntityFixture;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MovieEntityMapperTest {

    @Test
    void givenMovie_thenReturnMovieEntity() {
        Movie movie = MovieFixture.load();

        MovieEntityMapper movieEntityMapper = MovieEntityMapper.INSTANCE;

        MovieEntity result = movieEntityMapper.movieToMovieEntity(movie);

        assertThat(result).isNotNull().usingRecursiveComparison().ignoringExpectedNullFields().isEqualTo(movie);
    }

    @Test
    void givenMovieEntity_thenReturnMovie() {
        MovieEntity movieEntity = MovieEntityFixture.load();

        MovieEntityMapper movieEntityMapper = MovieEntityMapper.INSTANCE;

        Movie result = movieEntityMapper.movieEntityToMovie(movieEntity);

        assertThat(result).isNotNull().usingRecursiveComparison().ignoringExpectedNullFields().isEqualTo(movieEntity);
    }
}