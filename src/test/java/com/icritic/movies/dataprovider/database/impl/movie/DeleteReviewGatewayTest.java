package com.icritic.movies.dataprovider.database.impl.movie;

import com.icritic.movies.dataprovider.database.repository.ReviewEntityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DeleteReviewGatewayTest {

    @InjectMocks
    private DeleteReviewGateway deleteReviewGateway;

    @Mock
    private ReviewEntityRepository reviewEntityRepository;

    @Test
    void givenReviewId_whenDeleteReview_thenDeleteReview() {
        deleteReviewGateway.execute(1L);

        verify(reviewEntityRepository).deleteById(1L);
    }
}