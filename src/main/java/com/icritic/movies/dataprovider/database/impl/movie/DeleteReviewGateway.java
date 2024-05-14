package com.icritic.movies.dataprovider.database.impl.movie;

import com.icritic.movies.core.usecase.movie.DeleteReviewBoundary;
import com.icritic.movies.dataprovider.database.repository.ReviewEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteReviewGateway implements DeleteReviewBoundary {

    private final ReviewEntityRepository reviewEntityRepository;

    public void execute(Long reviewId) {
        reviewEntityRepository.deleteById(reviewId);
    }
}
