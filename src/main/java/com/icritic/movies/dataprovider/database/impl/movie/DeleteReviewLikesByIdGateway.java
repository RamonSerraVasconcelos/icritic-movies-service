package com.icritic.movies.dataprovider.database.impl.movie;

import com.icritic.movies.core.usecase.movie.DeleteReviewLikesByIdBoundary;
import com.icritic.movies.dataprovider.database.repository.ReviewEntityLikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteReviewLikesByIdGateway implements DeleteReviewLikesByIdBoundary {

    private final ReviewEntityLikesRepository reviewEntityRepository;

    public void execute(Long reviewId) {
        reviewEntityRepository.deleteLikesByReviewId(reviewId);
    }
}
