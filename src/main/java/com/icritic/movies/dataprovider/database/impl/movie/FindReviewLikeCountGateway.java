package com.icritic.movies.dataprovider.database.impl.movie;

import com.icritic.movies.core.usecase.movie.FindReviewLikeCountBoundary;
import com.icritic.movies.dataprovider.database.repository.ReviewEntityLikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindReviewLikeCountGateway implements FindReviewLikeCountBoundary {

    private final ReviewEntityLikesRepository reviewEntityLikesRepository;

    @Override
    public Integer execute(Long reviewId) {
        return reviewEntityLikesRepository.countByReviewId(reviewId);
    }
}
