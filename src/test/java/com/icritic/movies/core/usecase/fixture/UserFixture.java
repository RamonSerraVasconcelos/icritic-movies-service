package com.icritic.movies.core.usecase.fixture;

import com.icritic.movies.core.model.User;

public class UserFixture {

    public static User load() {
        return User.builder()
                .id(1L)
                .name("name")
                .profilePictureUrl("profilePictureUrl")
                .active(true)
                .build();
    }
}
