package com.icritic.movies.core.usecase.movie;

import com.icritic.movies.core.model.Movie;
import com.icritic.movies.core.usecase.fixture.MovieFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindAllMoviesUseCaseTest {

    @InjectMocks
    private FindAllMoviesUseCase findAllMoviesUseCase;

    @Mock
    private FindAllMoviesBoundary findAllMoviesBoundary;

    @Test
    void givenExecution_thenFind_andReturnAllMovies() {
        List<Movie> movies = List.of(MovieFixture.load(), MovieFixture.load());

        when(findAllMoviesBoundary.execute()).thenReturn(movies);

        List<Movie> result = findAllMoviesUseCase.execute();

        assertThat(result).isNotNull().isNotEmpty().hasSize(2);
        assertThat(result.get(0).getName()).isEqualTo(movies.get(0).getName());
    }
}