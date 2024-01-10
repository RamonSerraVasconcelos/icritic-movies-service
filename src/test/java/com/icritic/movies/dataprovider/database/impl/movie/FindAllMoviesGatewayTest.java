package com.icritic.movies.dataprovider.database.impl.movie;

import com.icritic.movies.core.model.Movie;
import com.icritic.movies.dataprovider.database.entity.MovieEntity;
import com.icritic.movies.dataprovider.database.repository.MovieEntityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindAllMoviesGatewayTest {

    @InjectMocks
    private FindAllMoviesGateway findAllMoviesGateway;

    @Mock
    private MovieEntityRepository movieEntityRepository;

    @Mock
    private Pageable pageable;

    @Mock
    private Page<MovieEntity> pageableMovies;

    @Test
    void givenExecution_thenReturnAllMovies() {
        when(movieEntityRepository.findAllByActiveOrderByIdAsc(pageable, true)).thenReturn(pageableMovies);

        Page<Movie> movies = findAllMoviesGateway.execute(pageable);

        verify(movieEntityRepository).findAllByActiveOrderByIdAsc(pageable, true);

        assertThat(movies).isNotNull();
    }
}