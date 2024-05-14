package com.icritic.movies.core.usecase.movie;

public interface CheckUserReviewLikeCountBoundary {

    Integer execute(Long reviewId, Long userId);
}
