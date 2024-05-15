package com.icritic.movies.core.usecase.movie;

import com.icritic.movies.core.model.Review;
import com.icritic.movies.core.model.User;
import com.icritic.movies.core.usecase.fixture.ReviewFixture;
import com.icritic.movies.core.usecase.fixture.UserFixture;
import com.icritic.movies.core.usecase.user.FindUserByIdBoundary;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindReviewsUseCaseTest {

    @InjectMocks
    private FindReviewsUseCase findReviewsUseCase;

    @Mock
    private FindReviewsByMovieIdBoundary findReviewsByMovieIdBoundary;

    @Mock
    private FindUserByIdBoundary findUserByIdBoundary;

    @Mock
    private Pageable pageable;

    @Test
    void givenValidParams_whenReviewsAreFound_thenReturnReviews() {
        List<Review> reviews = List.of(ReviewFixture.load(), ReviewFixture.load(), ReviewFixture.load());

        User inactiveUser = UserFixture.load();
        inactiveUser.setActive(false);

        Page<Review> reviewsFromDb = new PageImpl<>(reviews, pageable, 2);

        when(findReviewsByMovieIdBoundary.execute(pageable, 1L)).thenReturn(reviewsFromDb);
        when(findUserByIdBoundary.execute(anyLong(), anyString()))
                .thenReturn(Optional.of(UserFixture.load()))
                .thenReturn(Optional.empty())
                .thenReturn(Optional.of(inactiveUser));

        Page<Review> result = findReviewsUseCase.execute(pageable, 1L, "authorizationToken");

        verify(findReviewsByMovieIdBoundary).execute(pageable, 1L);
        verify(findUserByIdBoundary, times(3)).execute(anyLong(), anyString());

        assertThat(result).isNotNull().hasSize(1);
        assertThat(result.getContent().get(0).getId()).isEqualTo(reviews.get(0).getId());
        assertThat(result.getContent().get(0).getReview()).isEqualTo(reviews.get(0).getReview());
        assertThat(result.getContent().get(0).getUser().getId()).isEqualTo(reviews.get(0).getUser().getId());
    }

    @Test
    void givenValidParameters_WhenReviewsAreEmpty_ThenReturnEmptyPage() {
        Page<Review> reviewsFromDb = new PageImpl<>(List.of(), pageable, 0);
        when(findReviewsByMovieIdBoundary.execute(pageable, 1L)).thenReturn(reviewsFromDb);

        Page<Review> result = findReviewsUseCase.execute(pageable, 1L, "authorizationToken");

        verify(findReviewsByMovieIdBoundary).execute(pageable, 1L);
        verifyNoInteractions(findUserByIdBoundary);

        assertThat(result).isNotNull().hasSize(0);
    }
}