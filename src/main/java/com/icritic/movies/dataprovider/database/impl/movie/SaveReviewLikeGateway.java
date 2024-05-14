package com.icritic.movies.dataprovider.database.impl.movie;

import com.icritic.movies.core.usecase.movie.SaveReviewLikeBoundary;
import com.icritic.movies.dataprovider.database.repository.ReviewEntityLikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SaveReviewLikeGateway implements SaveReviewLikeBoundary {

    private final ReviewEntityLikesRepository reviewEntityLikesRepository;

    public void execute(Long reviewId, Long userId) {
        reviewEntityLikesRepository.save(reviewId, userId);
    }
}
