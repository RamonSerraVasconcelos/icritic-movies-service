package com.icritic.movies.dataprovider.database.impl.movie;

import com.icritic.movies.core.model.Movie;
import com.icritic.movies.core.model.MovieFilter;
import com.icritic.movies.core.usecase.fixture.MovieFilterFixture;
import com.icritic.movies.dataprovider.database.entity.MovieEntity;
import com.icritic.movies.dataprovider.database.fixture.MovieEntityFixture;
import com.icritic.movies.dataprovider.database.repository.MovieEntityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindAllMoviesGatewayTest {

    @InjectMocks
    private FindAllMoviesGateway findAllMoviesGateway;

    @Mock
    private MovieEntityRepository movieEntityRepository;

    @Test
    void givenExecution_thenReturnAllMovies() {
        MovieFilter movieFilter = MovieFilterFixture.load();
        List<MovieEntity> movieEntities = List.of(MovieEntityFixture.load(), MovieEntityFixture.load());

        when(movieEntityRepository.findByParams(movieFilter)).thenReturn(movieEntities);

        List<Movie> movies = findAllMoviesGateway.execute(movieFilter);

        verify(movieEntityRepository).findByParams(movieFilter);

        assertThat(movies).isNotNull();
    }
}