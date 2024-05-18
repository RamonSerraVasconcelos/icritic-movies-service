package com.icritic.movies.core.usecase.movie;

import com.icritic.movies.core.model.Review;

import java.util.Optional;

public interface FindReviewByIdBoundary {

    Optional<Review> execute(Long reviewId);
}
