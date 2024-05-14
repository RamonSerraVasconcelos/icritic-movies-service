package com.icritic.movies.dataprovider.database.impl.movie;

import com.icritic.movies.core.usecase.movie.CheckUserReviewLikeCountBoundary;
import com.icritic.movies.dataprovider.database.repository.ReviewEntityLikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
@RequiredArgsConstructor
public class CheckUserReviewLikeCountGateway implements CheckUserReviewLikeCountBoundary {

    private final ReviewEntityLikesRepository reviewEntityLikesRepository;

    public Integer execute(Long reviewId, Long userId) {
        Integer count = reviewEntityLikesRepository.countUserLikesPerReview(reviewId, userId);

        return isNull(count) ? 0 : count;
    }
}
