package com.icritic.movies.dataprovider.database.impl.movie;

import com.icritic.movies.dataprovider.database.repository.ReviewEntityLikesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DeleteReviewLikeGatewayTest {

    @InjectMocks
    private DeleteReviewLikeGateway deleteReviewLikeGateway;

    @Mock
    private ReviewEntityLikesRepository reviewEntityLikesRepository;

    @Test
    void givenExecution_thenCallRepositoryDeleteMethod() {
        deleteReviewLikeGateway.execute(1L, 1L);

        verify(reviewEntityLikesRepository).delete(1L, 1L);
    }
}