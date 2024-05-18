package com.icritic.movies.dataprovider.database.impl.movie;

import com.icritic.movies.core.model.Review;
import com.icritic.movies.core.usecase.fixture.ReviewFixture;
import com.icritic.movies.dataprovider.database.entity.ReviewEntity;
import com.icritic.movies.dataprovider.database.fixture.ReviewEntityFixture;
import com.icritic.movies.dataprovider.database.repository.ReviewEntityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SaveReviewGatewayTest {

    @InjectMocks
    private SaveReviewGateway saveReviewGateway;

    @Mock
    private ReviewEntityRepository reviewEntityRepository;

    @Test
    void givenExecution_thenSave_andReturnReview() {
        ReviewEntity reviewEntity = ReviewEntityFixture.load();

        when(reviewEntityRepository.save(any(ReviewEntity.class))).thenReturn(reviewEntity);

        Review review = saveReviewGateway.execute(ReviewFixture.load());

        verify(reviewEntityRepository).save(any(ReviewEntity.class));

        assertThat(review).usingRecursiveComparison().ignoringFields("user", "movie", "likeCount").isEqualTo(reviewEntity);
    }
}