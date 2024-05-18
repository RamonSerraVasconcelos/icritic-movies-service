package com.icritic.movies.core.usecase.movie;

import com.icritic.movies.core.model.Review;
import com.icritic.movies.core.model.User;
import com.icritic.movies.core.model.enums.Role;
import com.icritic.movies.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeleteReviewUseCase {

    private final FindReviewByIdBoundary findReviewByIdBoundary;

    private final DeleteReviewBoundary deleteReviewBoundary;

    private final DeleteReviewLikesByIdBoundary deleteReviewLikesByIdBoundary;

    public void execute(Long reviewId, Long requestUserId, Role requestUserRole) {
        log.info("Deleting review with id: [{}]. Requested by user: [{}]", reviewId, requestUserId);

        try {
            Optional<Review> reviewOptional = findReviewByIdBoundary.execute(reviewId);

            if (reviewOptional.isEmpty()) {
                throw new ResourceNotFoundException("Review not found");
            }

            if (!Arrays.asList(Role.ADMIN, Role.MODERATOR).contains(requestUserRole)) {
                if (reviewOptional.map(Review::getUser)
                        .map(User::getId)
                        .filter(id -> id.equals(requestUserId))
                        .isEmpty()) {

                    log.warn("User with id: [{}] is not authorized to delete review with id: [{}]", requestUserId, reviewId);
                    throw new ResourceNotFoundException("Review not found");
                }
            }

            deleteReviewBoundary.execute(reviewId);

            deleteReviewLikesByIdBoundary.execute(reviewId);
        } catch (Exception e) {
            log.error("Error deleting review with id: [{}] and requested by user: [{}]", reviewId, requestUserId, e);
            throw e;
        }
    }
}
