package com.icritic.movies.dataprovider.database.repository.impl;

import com.icritic.movies.dataprovider.database.repository.ReviewEntityLikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ReviewEntityLikesImpl implements ReviewEntityLikesRepository {

    private final JdbcTemplate jdbcTemplate;

    public void save(Long reviewId, Long userId) {
        String query = "INSERT INTO review_likes (review_id, user_id) VALUES (?, ?)";
        jdbcTemplate.update(query, reviewId, userId);
    }

    public Integer countUserLikesPerReview(Long reviewId, Long userId) {
        String query = "SELECT COUNT(*) FROM review_likes WHERE review_id = ? AND user_id = ?";
        return jdbcTemplate.queryForObject(query, Integer.class, reviewId, userId);
    }

    public void delete(Long reviewId, Long userId) {
        String query = "DELETE FROM review_likes WHERE review_id = ? AND user_id = ?";
        jdbcTemplate.update(query, reviewId, userId);
    }

    public void deleteLikesByReviewId(Long reviewId) {
        String query = "DELETE FROM review_likes WHERE review_id = ?";
        jdbcTemplate.update(query, reviewId);
    }

    public Integer countByReviewId(Long reviewId) {
        String query = "SELECT COUNT(*) FROM review_likes WHERE review_id = ?";
        return jdbcTemplate.queryForObject(query, Integer.class, reviewId);
    }
}
