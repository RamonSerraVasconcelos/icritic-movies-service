package com.icritic.movies.core.usecase.actor;

import com.icritic.movies.core.model.Actor;
import com.icritic.movies.core.model.Country;
import com.icritic.movies.core.usecase.country.FindCountryByIdBoundary;
import com.icritic.movies.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindActorByIdUseCase {

    private final FindActorByIdBoundary findActorByIdBoundary;

    private final FindCountryByIdBoundary findCountryByIdBoundary;

    public Actor execute(Long id) {
        try {
            log.info("Finding actor with id: [{}]", id);

            Optional<Actor> optionalActor = findActorByIdBoundary.execute(id);

            if (optionalActor.isEmpty()) {
               throw new ResourceNotFoundException("Actor not found");
            }

            Actor actor = optionalActor.get();

            Optional<Country> optionalCountry = findCountryByIdBoundary.execute(actor.getCountry().getId());

            optionalCountry.ifPresent(actor::setCountry);

            return actor;
        } catch (Exception e) {
            log.error("Error finding actor with id: [{}]", id, e);
            throw e;
        }
    }
}
