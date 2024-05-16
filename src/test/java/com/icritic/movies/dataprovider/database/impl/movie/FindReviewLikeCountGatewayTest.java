package com.icritic.movies.dataprovider.database.impl.movie;

import com.icritic.movies.dataprovider.database.repository.ReviewEntityLikesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindReviewLikeCountGatewayTest {

    @InjectMocks
    private FindReviewLikeCountGateway findReviewLikeCountGateway;

    @Mock
    private ReviewEntityLikesRepository reviewEntityLikesRepository;

    @Test
    void givenExecution_thenFind_andReturnCount() {
        when(reviewEntityLikesRepository.countByReviewId(1L)).thenReturn(2);

        Integer result = findReviewLikeCountGateway.execute(1L);
        
        assertThat(result).isEqualTo(2);
    }
}