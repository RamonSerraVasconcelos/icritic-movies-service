package com.icritic.movies.core.usecase.actor;

import com.icritic.movies.core.model.Actor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import static java.util.Objects.nonNull;

@Component
@RequiredArgsConstructor
@Slf4j
public class FindAllActorsUseCase {

    private final FindAllActorsBoundary findAllActorsBoundary;

    private final FindAllActorsCachedBoundary findAllActorsCachedBoundary;

    private final SaveActorsToCacheBoundary saveActorsToCacheBoundary;

    public Page<Actor> execute(Pageable pageable) {
        log.info("Loading all actors");

        Page<Actor> cachedActors = findAllActorsCachedBoundary.execute(pageable);

        if (nonNull(cachedActors)) {
            return cachedActors;
        }

        Page<Actor> actors = findAllActorsBoundary.execute(pageable);
        saveActorsToCacheBoundary.execute(actors);

        return actors;
    }
}
