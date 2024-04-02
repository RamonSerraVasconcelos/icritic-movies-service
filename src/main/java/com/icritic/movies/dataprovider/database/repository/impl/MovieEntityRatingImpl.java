package com.icritic.movies.dataprovider.database.repository.impl;

import com.icritic.movies.dataprovider.database.repository.MovieEntityRatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MovieEntityRatingImpl implements MovieEntityRatingRepository {

    private final JdbcTemplate jdbcTemplate;

    public void upsert(Long movieId, Long userId, int rating) {
        String query = "INSERT INTO movie_ratings (movie_id, user_id, rating) VALUES (?, ?, ?) ON CONFLICT (movie_id, user_id) DO UPDATE SET rating = ?";
        jdbcTemplate.update(query, movieId, userId, rating, rating);
    }
}
