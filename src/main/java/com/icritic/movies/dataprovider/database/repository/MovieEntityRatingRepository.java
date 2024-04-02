package com.icritic.movies.dataprovider.database.repository;

public interface MovieEntityRatingRepository {

    void upsert(Long movieId, Long userId, int rating);
}
