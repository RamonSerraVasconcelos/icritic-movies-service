package com.icritic.movies.dataprovider.database.impl.movie;

import com.icritic.movies.core.model.Review;
import com.icritic.movies.core.usecase.movie.FindReviewsByMovieIdBoundary;
import com.icritic.movies.dataprovider.database.entity.ReviewEntity;
import com.icritic.movies.dataprovider.database.mapper.ReviewEntityMapper;
import com.icritic.movies.dataprovider.database.repository.ReviewEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FindReviewsByMovieIdGateway implements FindReviewsByMovieIdBoundary {

    private final ReviewEntityRepository reviewEntityRepository;

    public Page<Review> execute(Pageable pageable, Long movieId) {
        ReviewEntityMapper mapper = ReviewEntityMapper.INSTANCE;

        Page<ReviewEntity> pageableReviews = reviewEntityRepository.findAllByMovieIdOrderByIdDesc(pageable, movieId);

        List<Review> reviews = pageableReviews.getContent()
                .stream()
                .map(mapper::reviewEntityToReview)
                .collect(Collectors.toList());

        return new PageImpl<>(reviews, pageable, pageableReviews.getTotalElements());
    }
}
