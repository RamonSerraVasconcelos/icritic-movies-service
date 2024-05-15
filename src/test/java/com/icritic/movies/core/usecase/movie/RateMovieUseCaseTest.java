package com.icritic.movies.core.usecase.movie;

import com.icritic.movies.core.model.Movie;
import com.icritic.movies.core.usecase.fixture.MovieFixture;
import com.icritic.movies.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RateMovieUseCaseTest {

    @InjectMocks
    private RateMovieUseCase rateMovieUseCase;

    @Mock
    private FindMovieByIdBoundary findMovieByIdBoundary;

    @Mock
    private UpsertMovieRateBoundary upsertMovieRateBoundary;

    @Test
    void givenValidParams_whenMovieIsFound_thenRateMovie() {
        Movie movie = MovieFixture.load();

        when(findMovieByIdBoundary.execute(1L)).thenReturn(Optional.of(movie));

        rateMovieUseCase.execute(1L, 1L, 3);

        verify(findMovieByIdBoundary).execute(1L);
        verify(upsertMovieRateBoundary).execute(1L, 1L, 3);
    }

    @Test
    void givenInvalidParams_whenMovieIsNotFound_thenThrowException() {
        when(findMovieByIdBoundary.execute(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> rateMovieUseCase.execute(1L, 1L, 3));

        verify(findMovieByIdBoundary).execute(1L);
        verifyNoInteractions(upsertMovieRateBoundary);
    }

}