package com.icritic.movies.core.usecase.user;

import com.icritic.movies.core.model.User;

import java.util.Optional;

public interface FindUserByIdBoundary {

    Optional<User> execute(Long userId, String authorisationToken);
}
