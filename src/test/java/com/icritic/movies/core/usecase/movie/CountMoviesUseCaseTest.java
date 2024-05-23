package com.icritic.movies.core.usecase.movie;

import com.icritic.movies.core.model.MovieFilter;
import com.icritic.movies.core.usecase.fixture.MovieFilterFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CountMoviesUseCaseTest {

    @InjectMocks
    private CountMoviesUseCase countMoviesUseCase;

    @Mock
    private FindMovieCountCachedBoundary findMovieCountCachedBoundary;

    @Mock
    private CountMoviesBoundary countMoviesBoundary;

    @Mock
    private SaveMovieCountToCacheBoundary saveMovieCountToCacheBoundary;

    @Test
    void givenCacheableFilter_whenResultIsNotOnCache_thenCacheAndReturnCount() {
        MovieFilter movieFilter = MovieFilterFixture.load();

        when(findMovieCountCachedBoundary.execute()).thenReturn(null);
        when(countMoviesBoundary.execute(movieFilter)).thenReturn(10L);

        Long count = countMoviesUseCase.execute(movieFilter);

        verify(findMovieCountCachedBoundary).execute();
        verify(countMoviesBoundary).execute(movieFilter);
        verify(saveMovieCountToCacheBoundary).execute(count);

        assertThat(count).isEqualTo(10L);
    }

    @Test
    void givenCacheableFilter_whenResultIsOnCache_thenReturnCount() {
        MovieFilter movieFilter = MovieFilterFixture.load();

        when(findMovieCountCachedBoundary.execute()).thenReturn(10L);

        Long count = countMoviesUseCase.execute(movieFilter);

        verify(findMovieCountCachedBoundary).execute();
        verifyNoInteractions(countMoviesBoundary);
        verifyNoInteractions(saveMovieCountToCacheBoundary);

        assertThat(count).isEqualTo(10L);
    }

    @Test
    void givenNonCacheableFilter_whenResultIsFound_thenReturnCount() {
        MovieFilter movieFilter = MovieFilterFixture.loadWithParams("Blade Runner", List.of(1), List.of(2), List.of(3));

        when(countMoviesBoundary.execute(movieFilter)).thenReturn(10L);

        Long count = countMoviesUseCase.execute(movieFilter);

        verify(countMoviesBoundary).execute(movieFilter);
        verifyNoInteractions(findMovieCountCachedBoundary);
        verifyNoInteractions(saveMovieCountToCacheBoundary);

        assertThat(count).isEqualTo(10L);
    }
}