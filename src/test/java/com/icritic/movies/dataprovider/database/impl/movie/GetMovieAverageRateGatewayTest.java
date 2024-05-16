package com.icritic.movies.dataprovider.database.impl.movie;

import com.icritic.movies.dataprovider.database.repository.MovieEntityRatingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetMovieAverageRateGatewayTest {

    @InjectMocks
    private GetMovieAverageRateGateway getMovieAverageRateGateway;

    @Mock
    private MovieEntityRatingRepository movieEntityRatingRepository;

    @Test
    void givenExecution_thenFind_andReturnAverageRate() {
        when(movieEntityRatingRepository.getAverageRate(1L)).thenReturn(5);

        getMovieAverageRateGateway.execute(1L);

        verify(movieEntityRatingRepository).getAverageRate(1L);
        assertThat(getMovieAverageRateGateway.execute(1L)).isEqualTo(5);
    }
}