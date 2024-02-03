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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindAllMoviesUseCaseTest {

    @InjectMocks
    private FindAllMoviesUseCase findAllMoviesUseCase;

    @Mock
    private FindAllMoviesBoundary findAllMoviesBoundary;

    @Mock
    private FindAllMoviesCachedBoundary findAllMoviesCachedBoundary;

    @Mock
    private SaveMoviesToCacheBoundary saveMoviesToCacheBoundary;

    @Mock
    private Pageable pageable;

    @Mock
    private Page<Movie> pageableMovies;

    @Test
    void givenExecution_whenMoviesAreNotOnCache_thenReturnAllMoviesByDatabase() {
        when(findAllMoviesCachedBoundary.execute(pageable)).thenReturn(null);
        when(findAllMoviesBoundary.execute(pageable)).thenReturn(pageableMovies);

        Page<Movie> result = findAllMoviesUseCase.execute(pageable);

        verify(findAllMoviesCachedBoundary).execute(pageable);
        verify(findAllMoviesBoundary).execute(pageable);
        verify(saveMoviesToCacheBoundary).execute(pageableMovies);

        assertThat(result).isNotNull();
    }

    @Test
    void givenExecution_whenMoviesAreOnCache_thenReturnAllMoviesFromCache() {
        when(findAllMoviesCachedBoundary.execute(pageable)).thenReturn(pageableMovies);

        Page<Movie> result = findAllMoviesUseCase.execute(pageable);

        verify(findAllMoviesCachedBoundary).execute(pageable);
        verifyNoInteractions(findAllMoviesBoundary);
        verifyNoInteractions(saveMoviesToCacheBoundary);

        assertThat(result).isNotNull();
    }
}