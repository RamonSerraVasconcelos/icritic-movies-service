package com.icritic.movies.core.usecase.movie;

import com.icritic.movies.core.model.Movie;
import com.icritic.movies.core.model.MovieFilter;
import com.icritic.movies.core.usecase.fixture.ActorFixture;
import com.icritic.movies.core.usecase.fixture.CategoryFixture;
import com.icritic.movies.core.usecase.fixture.DirectorFixture;
import com.icritic.movies.core.usecase.fixture.MovieFilterFixture;
import com.icritic.movies.core.usecase.fixture.MovieFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindAllMoviesUseCaseTest {

    @InjectMocks
    private FindAllMoviesUseCase findAllMoviesUseCase;

    @Mock
    private FindAllMoviesCachedBoundary findAllMoviesCachedBoundary;

    @Mock
    private FindAllMoviesBoundary findAllMoviesBoundary;

    @Mock
    private FindMovieCategoriesBoundary findMovieCategoriesBoundary;

    @Mock
    private FindMovieDirectorsBoundary findMovieDirectorsBoundary;

    @Mock
    private FindMovieActorsBoundary findMovieActorsBoundary;

    @Mock
    private CountMoviesUseCase countMoviesUseCase;

    @Mock
    private SaveMoviesToCacheBoundary saveMoviesToCacheBoundary;

    @Test
    void givenCacheableFilter_whenMoviesAreNotOnCache_thenCacheAndReturnResultsFromDb() {
        MovieFilter movieFilter = MovieFilterFixture.load();
        List<Movie> movies = List.of(MovieFixture.load(), MovieFixture.load());

        when(findAllMoviesCachedBoundary.execute(any(Pageable.class))).thenReturn(List.of());
        when(findAllMoviesBoundary.execute(movieFilter)).thenReturn(movies);

        when(findMovieCategoriesBoundary.execute(movies.get(0).getId()))
                .thenReturn(List.of(CategoryFixture.load()))
                .thenReturn(List.of(CategoryFixture.load()));
        when(findMovieDirectorsBoundary.execute(movies.get(0).getId()))
                .thenReturn(List.of(DirectorFixture.load()))
                .thenReturn(List.of(DirectorFixture.load()));
        when(findMovieActorsBoundary.execute(movies.get(0).getId()))
                .thenReturn(List.of(ActorFixture.load()))
                .thenReturn(List.of(ActorFixture.load()));

        Page<Movie> result = findAllMoviesUseCase.execute(movieFilter);

        verify(findAllMoviesCachedBoundary).execute(any(Pageable.class));
        verify(findAllMoviesBoundary).execute(movieFilter);
        verify(findMovieCategoriesBoundary, times(2)).execute(movies.get(0).getId());
        verify(findMovieDirectorsBoundary, times(2)).execute(movies.get(0).getId());
        verify(findMovieActorsBoundary, times(2)).execute(movies.get(0).getId());
        verify(saveMoviesToCacheBoundary).execute(result);

        assertThat(result).isNotEmpty().hasSize(2);
        assertThat(result.getContent().get(0).getId()).isEqualTo(movies.get(0).getId());
        assertThat(result.getContent().get(1).getId()).isEqualTo(movies.get(1).getId());
    }

    @Test
    void givenCacheableFilter_whenMoviesAreOnCache_thenReturnResultsFromCache() {
        MovieFilter movieFilter = MovieFilterFixture.load();
        List<Movie> movies = List.of(MovieFixture.load(), MovieFixture.load());

        when(findAllMoviesCachedBoundary.execute(any(Pageable.class))).thenReturn(movies);

        Page<Movie> result = findAllMoviesUseCase.execute(movieFilter);

        verify(findAllMoviesCachedBoundary).execute(any(Pageable.class));
        verifyNoInteractions(findAllMoviesBoundary);
        verifyNoInteractions(findMovieCategoriesBoundary);
        verifyNoInteractions(findMovieDirectorsBoundary);
        verifyNoInteractions(findMovieActorsBoundary);
        verifyNoInteractions(saveMoviesToCacheBoundary);

        assertThat(result).isNotEmpty().hasSize(2);
        assertThat(result.getContent().get(0).getId()).isEqualTo(movies.get(0).getId());
        assertThat(result.getContent().get(1).getId()).isEqualTo(movies.get(1).getId());
    }

    @Test
    void givenNonCacheableFilter_thenReturnResultsFromDb() {
        MovieFilter movieFilter = MovieFilterFixture.loadWithParams("Blade Runner", List.of(1), List.of(2), List.of(3));
        List<Movie> movies = List.of(MovieFixture.load(), MovieFixture.load());

        when(findAllMoviesBoundary.execute(movieFilter)).thenReturn(movies);
        when(findMovieCategoriesBoundary.execute(movies.get(0).getId()))
                .thenReturn(List.of(CategoryFixture.load()))
                .thenReturn(List.of(CategoryFixture.load()));
        when(findMovieDirectorsBoundary.execute(movies.get(0).getId()))
                .thenReturn(List.of(DirectorFixture.load()))
                .thenReturn(List.of(DirectorFixture.load()));
        when(findMovieActorsBoundary.execute(movies.get(0).getId()))
                .thenReturn(List.of(ActorFixture.load()))
                .thenReturn(List.of(ActorFixture.load()));

        Page<Movie> result = findAllMoviesUseCase.execute(movieFilter);

        verify(findAllMoviesBoundary).execute(movieFilter);
        verify(findMovieCategoriesBoundary, times(2)).execute(movies.get(0).getId());
        verify(findMovieDirectorsBoundary, times(2)).execute(movies.get(0).getId());
        verify(findMovieActorsBoundary, times(2)).execute(movies.get(0).getId());
        verifyNoInteractions(findAllMoviesCachedBoundary);
        verifyNoInteractions(saveMoviesToCacheBoundary);

        assertThat(result).isNotEmpty().hasSize(2);
        assertThat(result.getContent().get(0).getId()).isEqualTo(movies.get(0).getId());
        assertThat(result.getContent().get(1).getId()).isEqualTo(movies.get(1).getId());
    }
}