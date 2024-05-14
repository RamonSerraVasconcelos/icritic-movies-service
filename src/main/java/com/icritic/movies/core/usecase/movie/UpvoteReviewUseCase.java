package com.icritic.movies.core.usecase.movie;

import com.icritic.movies.core.model.enums.ReviewLikeAction;
import com.icritic.movies.exception.ResourceViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpvoteReviewUseCase {

    private final CheckUserReviewLikeCountBoundary checkUserReviewLikeCountBoundary;

    private final DeleteReviewLikeBoundary deleteReviewLikeBoundary;

    private final SaveReviewLikeBoundary saveReviewLikeBoundary;

    public void execute(Long reviewId, Long userId, ReviewLikeAction action) {
        log.info("Upvoting review with id: [{}] for user with id: [{}] and action: [{}]", reviewId, userId, action);

        switch (action) {
            case LIKE:
                saveReviewLike(reviewId, userId);
                break;
            case UNLIKE:
                deleteReviewLike(reviewId, userId);
                break;
        }
    }

    private void deleteReviewLike(Long reviewId, Long userId) {
        deleteReviewLikeBoundary.execute(reviewId, userId);
    }

    private void saveReviewLike(Long reviewId, Long userId) {
        int likeCount = checkUserReviewLikeCountBoundary.execute(reviewId, userId);

        if (likeCount > 0) {
            log.warn("User with id: [{}] already liked review with id: [{}]", userId, reviewId);
            throw new ResourceViolationException("Review already liked");
        }

        saveReviewLikeBoundary.execute(reviewId, userId);
    }
}
