package com.icritic.movies.core.usecase.actor;

import com.icritic.movies.core.model.Actor;

public interface SaveActorBoundary {

    Actor execute(Actor actor);
}
