package com.icritic.movies.core.usecase.actor;

import com.icritic.movies.core.model.Actor;
import com.icritic.movies.core.model.Country;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreateActorUseCase {

    private final SaveActorBoundary saveActorBoundary;

    private final InvalidateActorsCacheBoundary invalidateActorsCacheBoundary;

    public Actor execute(String name, String description, Long countryId) {
        try {
            log.info("Creating Actor with name: {}, description: {}, countryId: {}", name, description, countryId);

            Country country = Country.builder()
                    .id(countryId)
                    .build();

            Actor actor = Actor.builder()
                    .name(name)
                    .description(description)
                    .country(country)
                    .build();

            Actor savedActor = saveActorBoundary.execute(actor);

            invalidateActorsCacheBoundary.execute();

            return savedActor;
        } catch (Exception e) {
            log.error("Error creating actor", e);
            throw e;
        }
    }
}
