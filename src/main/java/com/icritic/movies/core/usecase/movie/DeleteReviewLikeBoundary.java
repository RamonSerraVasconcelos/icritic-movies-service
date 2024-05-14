package com.icritic.movies.core.usecase.movie;

public interface DeleteReviewLikeBoundary {

    void execute(Long reviewId, Long userId);
}
