package com.icritic.movies.core.usecase.movie;

import com.icritic.movies.core.model.Movie;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindAllMoviesUseCaseTest {

    @InjectMocks
    private FindAllMoviesUseCase findAllMoviesUseCase;

    @Mock
    private FindAllMoviesBoundary findAllMoviesBoundary;

    @Mock
    private Pageable pageable;

    @Mock
    private Page<Movie> pageableMovies;

    @Test
    void givenExecution_thenFind_andReturnAllMovies() {
        when(findAllMoviesBoundary.execute(pageable)).thenReturn(pageableMovies);

        Page<Movie> result = findAllMoviesUseCase.execute(pageable);

        assertThat(result).isNotNull();
    }
}