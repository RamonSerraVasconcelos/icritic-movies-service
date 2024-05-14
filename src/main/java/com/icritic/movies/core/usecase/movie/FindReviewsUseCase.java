package com.icritic.movies.core.usecase.movie;

import com.icritic.movies.core.model.Review;
import com.icritic.movies.core.model.User;
import com.icritic.movies.core.usecase.user.FindUserByIdBoundary;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindReviewsUseCase {

    private final FindReviewsByMovieIdBoundary findReviewsByMovieIdBoundary;

    private final FindUserByIdBoundary findUserByIdBoundary;

    public Page<Review> execute(Pageable pageable, Long movieId, String authorisationToken) {
        log.info("Finding reviews for movie with id: [{}]", movieId);

        try {
            Page<Review> reviews = findReviewsByMovieIdBoundary.execute(pageable, movieId);

            List<Review> filteredReviews = filterReviewsWithValidUsers(reviews, authorisationToken);

            return new PageImpl<>(filteredReviews, pageable, filteredReviews.size());
        } catch (Exception e) {
            log.error("Error finding reviews for movie with id: [{}]", movieId, e);
            throw e;
        }
    }

    private List<Review> filterReviewsWithValidUsers(Page<Review> reviews, String authorisationToken) {
        List<Review> filteredReviews = new ArrayList<>(reviews.getContent());

        filteredReviews.removeIf(review -> {
            Optional<User> userOptional = findUserByIdBoundary.execute(review.getUser().getId(), authorisationToken);
            if (userOptional.isEmpty() || !userOptional.get().isActive()) {
                log.warn("User with id: [{}] not found, removing review [{}] from list", review.getUser().getId(), review.getId());
                return true;
            }

            review.setUser(userOptional.get());
            return false;
        });

        return filteredReviews;
    }
}
