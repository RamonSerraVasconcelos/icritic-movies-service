package com.icritic.movies.dataprovider.database.impl.movie;

import com.icritic.movies.core.model.Review;
import com.icritic.movies.core.usecase.movie.FindReviewByIdBoundary;
import com.icritic.movies.dataprovider.database.entity.ReviewEntity;
import com.icritic.movies.dataprovider.database.mapper.ReviewEntityMapper;
import com.icritic.movies.dataprovider.database.repository.ReviewEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FindReviewByIdGateway implements FindReviewByIdBoundary {

    private final ReviewEntityRepository reviewEntityRepository;

    @Override
    public Optional<Review> execute(Long reviewId) {
        ReviewEntityMapper mapper = ReviewEntityMapper.INSTANCE;

        Optional<ReviewEntity> reviewEntity = reviewEntityRepository.findById(reviewId);

        return reviewEntity.map(mapper::reviewEntityToReview);
    }
}
