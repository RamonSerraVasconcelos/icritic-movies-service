package com.icritic.movies.core.usecase.movie;

import com.icritic.movies.core.model.Movie;
import com.icritic.movies.core.model.Review;
import com.icritic.movies.exception.ResourceNotFoundException;
import com.icritic.movies.exception.ResourceViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewMovieUseCase {

    private final FindReviewByMovieAndUserIdBoundary findReviewByMovieAndUserIdBoundary;

    private final FindMovieByIdBoundary findMovieByIdBoundary;

    private final SaveReviewBoundary saveReviewBoundary;

    public Review execute(Long movieId, Long userId, String review) {
        try {
            Optional<Review> doesReviewExist = findReviewByMovieAndUserIdBoundary.execute(movieId, userId);
            if (doesReviewExist.isPresent()) {
                throw new ResourceViolationException("User already reviewed this movie");
            }

            Optional<Movie> movie = findMovieByIdBoundary.execute(movieId);
            if (movie.isEmpty()) {
                throw new ResourceNotFoundException("Movie not found");
            }

            Review reviewToSave = Review.builder()
                    .movie(movie.get())
                    .userId(userId)
                    .review(review)
                    .createdAt(LocalDateTime.now())
                    .build();

            return saveReviewBoundary.execute(reviewToSave);
        } catch (Exception e) {
            log.error("Error creating movie review for movieId: [{}], userId: [{}]", movieId, userId, e);
            throw e;
        }
    }
}
