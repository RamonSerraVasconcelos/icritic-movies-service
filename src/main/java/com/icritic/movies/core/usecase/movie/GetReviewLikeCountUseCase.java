package com.icritic.movies.core.usecase.movie;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetReviewLikeCountUseCase {

    private final FindReviewLikeCountBoundary findReviewLikeCountBoundary;

    public int execute(Long reviewId) {
        log.info("Finding likes for review with id: [{}]", reviewId);

        try {
            Integer likeCount = findReviewLikeCountBoundary.execute(reviewId);

            return likeCount == null ? 0 : likeCount;
        } catch (Exception e) {
            log.error("Error finding likes for review with id: [{}]", reviewId, e);
            return 0;
        }
    }
}
