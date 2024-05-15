package com.icritic.movies.core.usecase.movie;

import com.icritic.movies.core.model.Movie;
import com.icritic.movies.core.model.Review;
import com.icritic.movies.core.usecase.fixture.MovieFixture;
import com.icritic.movies.core.usecase.fixture.ReviewFixture;
import com.icritic.movies.exception.ResourceNotFoundException;
import com.icritic.movies.exception.ResourceViolationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReviewMovieUseCaseTest {

    @InjectMocks
    private ReviewMovieUseCase reviewMovieUseCase;

    @Mock
    private FindReviewByMovieAndUserIdBoundary findReviewByMovieAndUserIdBoundary;

    @Mock
    private FindMovieByIdBoundary findMovieByIdBoundary;

    @Mock
    private SaveReviewBoundary saveReviewBoundary;

    @Test
    void givenValidParameters_whenReviewsDoesntExist_andMovieExists_thenReturnReview() {
        Review review = ReviewFixture.load();
        Movie movie = MovieFixture.load();

        when(findReviewByMovieAndUserIdBoundary.execute(review.getMovie().getId(), review.getUser().getId())).thenReturn(Optional.empty());
        when(findMovieByIdBoundary.execute(review.getMovie().getId())).thenReturn(Optional.of(movie));
        when(saveReviewBoundary.execute(any(Review.class))).thenReturn(review);

        Review result = reviewMovieUseCase.execute(review.getMovie().getId(), review.getUser().getId(), review.getReview());

        verify(findReviewByMovieAndUserIdBoundary).execute(review.getMovie().getId(), review.getUser().getId());
        verify(findMovieByIdBoundary).execute(review.getMovie().getId());
        verify(saveReviewBoundary).execute(any(Review.class));

        assertThat(result).isNotNull();
        assertThat(result.getReview()).isEqualTo(review.getReview());
        assertThat(result.getMovie().getId()).isEqualTo(review.getMovie().getId());
        assertThat(result.getUser().getId()).isEqualTo(review.getUser().getId());
    }

    @Test
    void givenValidParameters_whenUserAlreadyReviewedMovie_thenThrowResourceViolationException() {
        Review review = ReviewFixture.load();

        when(findReviewByMovieAndUserIdBoundary.execute(review.getMovie().getId(), review.getUser().getId())).thenReturn(Optional.of(review));

        assertThrows(ResourceViolationException.class, () -> reviewMovieUseCase.execute(review.getMovie().getId(), review.getUser().getId(), review.getReview()));

        verify(findReviewByMovieAndUserIdBoundary).execute(review.getMovie().getId(), review.getUser().getId());
        verifyNoInteractions(findMovieByIdBoundary);
        verifyNoInteractions(saveReviewBoundary);
    }

    @Test
    void givenValidParameters_whenMovieIsNotFound_thenThrowResourceNotFoundException() {
        Review review = ReviewFixture.load();

        when(findReviewByMovieAndUserIdBoundary.execute(review.getMovie().getId(), review.getUser().getId())).thenReturn(Optional.empty());
        when(findMovieByIdBoundary.execute(review.getMovie().getId())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> reviewMovieUseCase.execute(review.getMovie().getId(), review.getUser().getId(), review.getReview()));

        verify(findReviewByMovieAndUserIdBoundary).execute(review.getMovie().getId(), review.getUser().getId());
        verify(findMovieByIdBoundary).execute(review.getMovie().getId());
        verifyNoInteractions(saveReviewBoundary);
    }
}