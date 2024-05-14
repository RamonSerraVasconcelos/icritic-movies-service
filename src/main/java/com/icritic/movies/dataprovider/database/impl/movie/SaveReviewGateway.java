package com.icritic.movies.dataprovider.database.impl.movie;

import com.icritic.movies.core.model.Review;
import com.icritic.movies.core.usecase.movie.SaveReviewBoundary;
import com.icritic.movies.dataprovider.database.entity.ReviewEntity;
import com.icritic.movies.dataprovider.database.mapper.ReviewEntityMapper;
import com.icritic.movies.dataprovider.database.repository.ReviewEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SaveReviewGateway implements SaveReviewBoundary {

    private final ReviewEntityRepository reviewEntityRepository;

    public Review execute(Review review) {
        ReviewEntityMapper mapper = ReviewEntityMapper.INSTANCE;

        ReviewEntity savedEntity = reviewEntityRepository.save(mapper.reviewToReviewEntity(review));

        return mapper.reviewEntityToReview(savedEntity);
    }
}
