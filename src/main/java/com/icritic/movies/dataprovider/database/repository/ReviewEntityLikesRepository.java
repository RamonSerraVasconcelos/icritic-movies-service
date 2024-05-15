package com.icritic.movies.dataprovider.database.repository;

public interface ReviewEntityLikesRepository {

    void save(Long reviewId, Long userId);

    Integer countUserLikesPerReview(Long reviewId, Long userId);

    void delete(Long reviewId, Long userId);

    void deleteLikesByReviewId(Long reviewId);
}
