package com.icritic.movies.core.usecase.actor;

import com.icritic.movies.core.model.Actor;

import java.util.Optional;

public interface FindActorByIdBoundary {

    Optional<Actor> execute(Long id);
}
