package com.icritic.movies.core.usecase.movie;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdateAverageMovieRatingUseCaseTest {

    @Mock
    private GetMovieAvarageRateBoundary getMovieAvarageRateBoundary;

    @Mock
    private UpdateAverageMovieRatingBoundary updateAverageMovieRatingBoundary;

    @InjectMocks
    private UpdateAverageMovieRatingUseCase updateAverageMovieRatingUseCase;

    @Test
    public void testExecute() {
        Long movieId = 1L;
        int expectedRating = 5;

        when(getMovieAvarageRateBoundary.execute(movieId)).thenReturn(expectedRating);

        int actualRating = updateAverageMovieRatingUseCase.execute(movieId);

        verify(getMovieAvarageRateBoundary, times(1)).execute(movieId);
        verify(updateAverageMovieRatingBoundary, times(1)).execute(movieId, expectedRating);

        assertEquals(expectedRating, actualRating);
    }
}