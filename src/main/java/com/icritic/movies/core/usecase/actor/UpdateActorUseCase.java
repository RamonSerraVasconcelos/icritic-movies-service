package com.icritic.movies.core.usecase.actor;

import com.icritic.movies.core.model.Actor;
import com.icritic.movies.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class UpdateActorUseCase {

    private final FindActorByIdBoundary findActorByIdBoundary;

    private final SaveActorBoundary saveActorBoundary;

    private final InvalidateActorsCacheBoundary invalidateActorsCacheBoundary;

    public Actor execute(Long id, String name, String description, Long countryId) {
        try {
            Optional<Actor> optionalActor = findActorByIdBoundary.execute(id);

            if(optionalActor.isEmpty()) {
                throw new ResourceNotFoundException("Actor not found");
            }

            Actor actor = optionalActor.get();

            actor.setName(name);
            actor.setDescription(description);
            actor.getCountry().setId(countryId);

            Actor updatedActor = saveActorBoundary.execute(actor);

            invalidateActorsCacheBoundary.execute();

            return updatedActor;
        } catch (Exception e) {
            log.error("Error updating actor with id: [{}]", id, e);
            throw e;
        }
    }
}
