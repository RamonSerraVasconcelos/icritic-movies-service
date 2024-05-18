package com.icritic.movies.dataprovider.database.impl.movie;

import com.icritic.movies.core.model.Review;
import com.icritic.movies.core.usecase.movie.FindReviewByMovieAndUserIdBoundary;
import com.icritic.movies.dataprovider.database.entity.ReviewEntity;
import com.icritic.movies.dataprovider.database.mapper.ReviewEntityMapper;
import com.icritic.movies.dataprovider.database.repository.ReviewEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FindReviewByMovieAndUserIdGateway implements FindReviewByMovieAndUserIdBoundary {

    private final ReviewEntityRepository reviewEntityRepository;

    public Optional<Review> execute(Long movieId, Long userId) {
        ReviewEntityMapper mapper = ReviewEntityMapper.INSTANCE;

        ReviewEntity reviewEntity = reviewEntityRepository.findByMovieIdAndUserId(movieId, userId);

        return Optional.ofNullable(reviewEntity).map(mapper::reviewEntityToReview);
    }
}
