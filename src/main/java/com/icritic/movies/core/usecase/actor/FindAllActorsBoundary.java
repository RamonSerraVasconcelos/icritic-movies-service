package com.icritic.movies.core.usecase.actor;

import com.icritic.movies.core.model.Actor;

import java.util.List;

public interface FindAllActorsBoundary {

    List<Actor> execute();
}
