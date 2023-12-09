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
public class FindActorByIdUseCase {

    private final FindActorByIdBoundary findActorByIdBoundary;

    public Actor execute(Long id) {
        try {
            log.info("Finding actor with id: [{}]", id);

            Optional<Actor> actor = findActorByIdBoundary.execute(id);

            if (actor.isEmpty()) {
               throw new ResourceNotFoundException("Actor not found");
            }

            return actor.get();
        } catch (Exception e) {
            log.error("Error finding actor with id: [{}]", id, e);
            throw e;
        }
    }
}
