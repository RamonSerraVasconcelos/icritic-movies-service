package com.icritic.movies.dataprovider.database.impl.movie;

import com.icritic.movies.dataprovider.database.repository.ReviewEntityLikesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DeleteReviewLikesByIdGatewayTest {

    @InjectMocks
    private DeleteReviewLikesByIdGateway deleteReviewLikesByIdGateway;

    @Mock
    private ReviewEntityLikesRepository reviewEntityRepository;

    @Test
    void givenExeciton_thenCallRepositoryDeleteMethod() {
        deleteReviewLikesByIdGateway.execute(1L);

        verify(reviewEntityRepository).deleteLikesByReviewId(1L);
    }
}