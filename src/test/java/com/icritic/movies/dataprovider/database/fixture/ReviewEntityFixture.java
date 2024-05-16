package com.icritic.movies.dataprovider.database.fixture;

import com.icritic.movies.core.model.User;
import com.icritic.movies.core.usecase.fixture.MovieFixture;
import com.icritic.movies.dataprovider.database.entity.MovieEntity;
import com.icritic.movies.dataprovider.database.entity.ReviewEntity;

import java.time.LocalDateTime;

public class ReviewEntityFixture {

    public static ReviewEntity load() {
        return ReviewEntity.builder()
                .id(1L)
                .userId(1L)
                .movie(MovieEntityFixture.load())
                .review("This a review for a movie")
                .createdAt(LocalDateTime.now())
                .build();
    }
}
