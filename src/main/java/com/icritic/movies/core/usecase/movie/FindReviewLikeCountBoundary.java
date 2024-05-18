package com.icritic.movies.core.usecase.movie;

public interface FindReviewLikeCountBoundary {

    Integer execute(Long reviewId);
}
