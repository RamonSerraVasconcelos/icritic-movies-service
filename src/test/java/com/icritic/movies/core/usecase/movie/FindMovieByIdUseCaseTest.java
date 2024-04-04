package com.icritic.movies.core.usecase.movie;

import com.icritic.movies.core.model.Country;
import com.icritic.movies.core.model.Movie;
import com.icritic.movies.core.usecase.country.FindCountryByIdBoundary;
import com.icritic.movies.core.usecase.fixture.CountryFixture;
import com.icritic.movies.core.usecase.fixture.MovieFixture;
import com.icritic.movies.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindMovieByIdUseCaseTest {

    @InjectMocks
    private FindMovieByIdUseCase findMovieByIdUseCase;

    @Mock
    private FindMovieByIdBoundary findMovieByIdBoundary;

    @Mock
    private FindCountryByIdBoundary findCountryByIdBoundary;

    @Mock
    private UpdateAverageMovieRatingUseCase updateAverageMovieRatingUseCase;

    @Mock
    private FindMovieCachedBoundary findMovieCachedBoundary;

    @Mock
    private SaveMovieToCacheBoundary saveMovieToCacheBoundary;

    @Test
    void givenValidId_whenMovieIsFoundOnCache_thenReturnMovie() {
        Movie movie = MovieFixture.load();

        when(findMovieCachedBoundary.execute(movie.getId())).thenReturn(movie);

        Movie result = findMovieByIdUseCase.execute(movie.getId());

        verifyNoInteractions(findMovieByIdBoundary);
        verifyNoInteractions(findCountryByIdBoundary);
        verifyNoInteractions(saveMovieToCacheBoundary);

        assertThat(result).isNotNull().usingRecursiveComparison().isEqualTo(movie);
    }

    @Test
    void givenValidId_whenMovieIsNotFoundOnCache_thenSearchOnDbAndReturnMovie() {
        Movie movie = MovieFixture.load();
        Country country = CountryFixture.load();

        when(findMovieByIdBoundary.execute(movie.getId())).thenReturn(Optional.of(movie));
        when(findCountryByIdBoundary.execute(country.getId())).thenReturn(Optional.of(country));
        when(updateAverageMovieRatingUseCase.execute(movie.getId())).thenReturn(10);
        when(findMovieCachedBoundary.execute(movie.getId())).thenReturn(null);

        Movie result = findMovieByIdUseCase.execute(movie.getId());

        verify(findMovieByIdBoundary).execute(movie.getId());
        verify(findCountryByIdBoundary, times(3)).execute(country.getId());
        verify(saveMovieToCacheBoundary).execute(movie);

        assertThat(result).isNotNull().usingRecursiveComparison().isEqualTo(movie);
    }

    @Test
    void givenValidId_whenMovieIsNotFound_thenThrowResourceNotFoundException() {
        when(findMovieByIdBoundary.execute(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> findMovieByIdUseCase.execute(1L));

        verify(findMovieByIdBoundary).execute(1L);
        verifyNoInteractions(findCountryByIdBoundary);
    }
}