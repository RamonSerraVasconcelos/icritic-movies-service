package com.icritic.movies.dataprovider.database.impl.movie;

import com.icritic.movies.core.model.Review;
import com.icritic.movies.dataprovider.database.entity.ReviewEntity;
import com.icritic.movies.dataprovider.database.fixture.ReviewEntityFixture;
import com.icritic.movies.dataprovider.database.repository.ReviewEntityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindReviewByMovieAndUserIdGatewayTest {

    @InjectMocks
    private FindReviewByMovieAndUserIdGateway findReviewByMovieAndUserIdGateway;

    @Mock
    private ReviewEntityRepository reviewEntityRepository;

    @Test
    void givenExecution_thenFinda_andReturnReview() {
        ReviewEntity reviewEntity = ReviewEntityFixture.load();

        when(reviewEntityRepository.findByMovieIdAndUserId(1L, 1L)).thenReturn(reviewEntity);

        Optional<Review> review = findReviewByMovieAndUserIdGateway.execute(1L, 1L);

        verify(reviewEntityRepository).findByMovieIdAndUserId(1L, 1L);
        assertThat(review).isPresent().get().usingRecursiveComparison().ignoringFields("user", "movie", "likeCount").isEqualTo(reviewEntity);
    }
}