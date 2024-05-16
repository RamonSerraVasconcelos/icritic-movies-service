package com.icritic.movies.dataprovider.database.impl.movie;

import com.icritic.movies.dataprovider.database.repository.MovieEntityRatingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UpsertMovieRateGatewayTest {

    @InjectMocks
    private UpsertMovieRateGateway upsertMovieRateGateway;

    @Mock
    private MovieEntityRatingRepository movieEntityRatingRepository;

    @Test
    void givenExecution_thenSaveRating() {
        upsertMovieRateGateway.execute(1L, 1L, 5);

        verify(movieEntityRatingRepository).upsert(1L, 1L, 5);
    }
}