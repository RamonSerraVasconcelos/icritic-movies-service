package com.icritic.movies.dataprovider.database.impl.movie;

import com.icritic.movies.core.model.Movie;
import com.icritic.movies.dataprovider.database.entity.MovieEntity;
import com.icritic.movies.dataprovider.database.fixture.MovieEntityFixture;
import com.icritic.movies.dataprovider.database.repository.MovieEntityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
        List<MovieEntity> moviesEntities = List.of(MovieEntityFixture.load(), MovieEntityFixture.load());

        when(movieEntityRepository.findAllByActiveOrderByIdAsc(true)).thenReturn(moviesEntities);

        List<Movie> movies = findAllMoviesGateway.execute();

        verify(movieEntityRepository).findAllByActiveOrderByIdAsc(true);

        assertThat(movies).isNotNull().isNotEmpty().hasSize(2);
        assertThat(movies.get(0).getName()).isEqualTo(moviesEntities.get(0).getName());
    }
}