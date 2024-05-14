package com.icritic.movies.core.usecase.movie;

import com.icritic.movies.core.model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FindReviewsByMovieIdBoundary {

    Page<Review> execute(Pageable pageable, Long movieId);
}
