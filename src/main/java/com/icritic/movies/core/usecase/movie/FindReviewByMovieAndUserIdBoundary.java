package com.icritic.movies.core.usecase.movie;

import com.icritic.movies.core.model.Review;

import java.util.Optional;

public interface FindReviewByMovieAndUserIdBoundary {

    Optional<Review> execute(Long movieId, Long userId);
}
