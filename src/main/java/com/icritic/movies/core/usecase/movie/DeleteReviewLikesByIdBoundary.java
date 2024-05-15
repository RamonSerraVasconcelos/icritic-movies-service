package com.icritic.movies.core.usecase.movie;

public interface DeleteReviewLikesByIdBoundary {

    void execute(Long reviewId);
}
