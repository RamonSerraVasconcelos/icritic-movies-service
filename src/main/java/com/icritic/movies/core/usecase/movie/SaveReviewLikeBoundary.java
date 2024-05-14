package com.icritic.movies.core.usecase.movie;

public interface SaveReviewLikeBoundary {

    void execute(Long reviewId, Long userId);
}
