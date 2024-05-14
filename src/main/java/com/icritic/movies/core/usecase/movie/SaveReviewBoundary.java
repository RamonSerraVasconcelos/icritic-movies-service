package com.icritic.movies.core.usecase.movie;

import com.icritic.movies.core.model.Review;

public interface SaveReviewBoundary {

    Review execute(Review review);
}
