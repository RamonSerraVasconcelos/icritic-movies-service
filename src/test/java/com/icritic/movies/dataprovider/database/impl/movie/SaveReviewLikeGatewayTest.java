package com.icritic.movies.dataprovider.database.impl.movie;

import com.icritic.movies.dataprovider.database.repository.ReviewEntityLikesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SaveReviewLikeGatewayTest {

    @InjectMocks
    private SaveReviewLikeGateway saveReviewLikeGateway;

    @Mock
    private ReviewEntityLikesRepository reviewEntityLikesRepository;

    @Test
    void givenExecution_thenSaveReviewLike() {
        saveReviewLikeGateway.execute(1L, 1L);

        verify(reviewEntityLikesRepository).save(1L, 1L);
    }
}