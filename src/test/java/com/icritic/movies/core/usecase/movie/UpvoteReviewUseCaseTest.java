package com.icritic.movies.core.usecase.movie;

import com.icritic.movies.core.model.enums.ReviewLikeAction;
import com.icritic.movies.core.usecase.fixture.ReviewFixture;
import com.icritic.movies.exception.ResourceNotFoundException;
import com.icritic.movies.exception.ResourceViolationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpvoteReviewUseCaseTest {

    @InjectMocks
    private UpvoteReviewUseCase upvoteReviewUseCase;

    @Mock
    private FindReviewByIdBoundary findReviewByIdBoundary;

    @Mock
    private CheckUserReviewLikeCountBoundary checkUserReviewLikeCountBoundary;

    @Mock
    private DeleteReviewLikeBoundary deleteReviewLikeBoundary;

    @Mock
    private SaveReviewLikeBoundary saveReviewLikeBoundary;

    @Test
    void givenValidParam_WhenActionIsLike_thenSaveReviewLike() {
        when(findReviewByIdBoundary.execute(anyLong())).thenReturn(Optional.of(ReviewFixture.load()));
        when(checkUserReviewLikeCountBoundary.execute(anyLong(), anyLong())).thenReturn(0);

        upvoteReviewUseCase.execute(1L, 1L, ReviewLikeAction.LIKE);

        verify(checkUserReviewLikeCountBoundary).execute(1L, 1L);
        verify(saveReviewLikeBoundary).execute(1L, 1L);
        verifyNoInteractions(deleteReviewLikeBoundary);
    }

    @Test
    void givenValidParam_WhenActionIsUnlike_thenDeleteReviewLike() {
        when(findReviewByIdBoundary.execute(anyLong())).thenReturn(Optional.of(ReviewFixture.load()));
        upvoteReviewUseCase.execute(1L, 1L, ReviewLikeAction.UNLIKE);

        verify(deleteReviewLikeBoundary).execute(1L, 1L);
        verifyNoInteractions(saveReviewLikeBoundary);
    }

    @Test
    void givenValidParams_whenUserAlreadyLikedReview_thenThrowException() {
        when(findReviewByIdBoundary.execute(anyLong())).thenReturn(Optional.of(ReviewFixture.load()));
        when(checkUserReviewLikeCountBoundary.execute(anyLong(), anyLong())).thenReturn(1);

        assertThrows(ResourceViolationException.class, () -> upvoteReviewUseCase.execute(1L, 1L, ReviewLikeAction.LIKE));

        verify(checkUserReviewLikeCountBoundary).execute(1L, 1L);
        verifyNoInteractions(saveReviewLikeBoundary);
        verifyNoInteractions(deleteReviewLikeBoundary);
    }

    @Test
    void givenValidParams_whenReviewDoesNotExist_thenThrowException() {
        when(findReviewByIdBoundary.execute(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> upvoteReviewUseCase.execute(1L, 1L, ReviewLikeAction.LIKE));

        verifyNoInteractions(checkUserReviewLikeCountBoundary);
        verifyNoInteractions(saveReviewLikeBoundary);
        verifyNoInteractions(deleteReviewLikeBoundary);
    }
}