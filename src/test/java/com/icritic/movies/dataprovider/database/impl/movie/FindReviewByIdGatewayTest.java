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
class FindReviewByIdGatewayTest {

    @InjectMocks
    private FindReviewByIdGateway findReviewByIdGateway;

    @Mock
    private ReviewEntityRepository reviewEntityRepository;

    @Test
    void givenExecution_whenFindReviewById_thenReturnReview() {
        ReviewEntity reviewEntity = ReviewEntityFixture.load();

        when(reviewEntityRepository.findById(reviewEntity.getId())).thenReturn(Optional.of(reviewEntity));

        Optional<Review> review = findReviewByIdGateway.execute(reviewEntity.getId());

        verify(reviewEntityRepository).findById(reviewEntity.getId());
        assertThat(review).isPresent().get().usingRecursiveComparison().ignoringFields("user", "movie").isEqualTo(reviewEntity);
    }
}