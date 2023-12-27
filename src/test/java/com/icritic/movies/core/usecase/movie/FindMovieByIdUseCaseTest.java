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

    @Test
    void givenValidId_whenMovieIsFound_thenReturnMovie() {
        Movie movie = MovieFixture.load();
        Country country = CountryFixture.load();

        when(findMovieByIdBoundary.execute(movie.getId())).thenReturn(Optional.of(movie));
        when(findCountryByIdBoundary.execute(country.getId())).thenReturn(Optional.of(country));

        Movie result = findMovieByIdUseCase.execute(movie.getId());

        verify(findMovieByIdBoundary).execute(movie.getId());
        verify(findCountryByIdBoundary, times(3)).execute(country.getId());

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