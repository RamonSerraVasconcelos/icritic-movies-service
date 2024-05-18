package com.icritic.movies.dataprovider.database.impl.movie;

import com.icritic.movies.dataprovider.database.repository.ReviewEntityLikesRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CheckUserReviewLikeCountGatewayTest {

    @InjectMocks
    private CheckUserReviewLikeCountGateway checkUserReviewLikeCountGateway;

    @Mock
    private ReviewEntityLikesRepository reviewEntityLikesRepository;

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void givenReviewIdAndUserId_whenExecute_thenReturnCount(Integer count) {
        Long reviewId = 1L;
        Long userId = 1L;

        when(reviewEntityLikesRepository.countUserLikesPerReview(reviewId, userId)).thenReturn(count);

        Integer result = checkUserReviewLikeCountGateway.execute(reviewId, userId);

        assertThat(result).isEqualTo(count);
    }
}