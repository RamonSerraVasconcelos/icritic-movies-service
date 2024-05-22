package com.icritic.movies.core.usecase.movie;

import com.icritic.movies.core.model.Actor;

import java.util.List;

public interface FindMovieActorsBoundary {

    List<Actor> execute(Long movieId);
}
