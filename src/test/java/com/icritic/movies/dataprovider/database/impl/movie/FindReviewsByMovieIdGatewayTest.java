package com.icritic.movies.dataprovider.database.impl.movie;

import com.icritic.movies.core.model.Review;
import com.icritic.movies.dataprovider.database.entity.ReviewEntity;
import com.icritic.movies.dataprovider.database.repository.ReviewEntityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindReviewsByMovieIdGatewayTest {

    @InjectMocks
    private FindReviewsByMovieIdGateway findReviewsByMovieIdGateway;

    @Mock
    private ReviewEntityRepository reviewEntityRepository;

    @Mock
    private Pageable pageable;

    @Mock
    private Page<ReviewEntity> pageableMovies;

    @Test
    void givenExecution_thenReturnAllMovies() {
        when(reviewEntityRepository.findAllByMovieIdOrderByIdDesc(pageable, 1L)).thenReturn(pageableMovies);

        Page<Review> movies = findReviewsByMovieIdGateway.execute(pageable, 1L);

        verify(reviewEntityRepository).findAllByMovieIdOrderByIdDesc(pageable, 1L);

        assertThat(movies).isNotNull();
    }
}