package com.icritic.movies.core.usecase.actor;

import com.icritic.movies.core.model.Actor;
import org.springframework.data.domain.Page;

public interface SaveActorsToCacheBoundary {

    void execute(Page<Actor> actors);
}
