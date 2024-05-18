package com.icritic.movies.core.usecase.fixture;

import com.icritic.movies.core.model.Review;
import com.icritic.movies.core.model.User;

import java.time.LocalDateTime;

public class ReviewFixture {

    public static Review load() {
        return Review.builder()
                .id(1L)
                .user(User.builder().id(1L).build())
                .movie(MovieFixture.load())
                .review("This a review for a movie")
                .createdAt(LocalDateTime.now())
                .build();
    }
}
