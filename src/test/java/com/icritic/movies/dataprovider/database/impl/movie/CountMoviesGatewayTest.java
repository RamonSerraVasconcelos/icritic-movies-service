package com.icritic.movies.dataprovider.database.impl.movie;

import com.icritic.movies.core.model.MovieFilter;
import com.icritic.movies.core.usecase.fixture.MovieFilterFixture;
import com.icritic.movies.dataprovider.database.repository.MovieEntityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CountMoviesGatewayTest {

    @InjectMocks
    private CountMoviesGateway countMoviesGateway;

    @Mock
    private MovieEntityRepository movieEntityRepository;

    @Test
    void givenExecution_thenReturnMovieCount() {
        MovieFilter movieFilter = MovieFilterFixture.load();

        when(movieEntityRepository.countMovies(movieFilter)).thenReturn(1L);

        Long result = countMoviesGateway.execute(movieFilter);

        verify(movieEntityRepository).countMovies(movieFilter);
        assertThat(result).isEqualTo(1L);
    }
}