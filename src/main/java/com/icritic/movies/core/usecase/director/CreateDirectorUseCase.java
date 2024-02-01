package com.icritic.movies.core.usecase.director;

import com.icritic.movies.core.model.Country;
import com.icritic.movies.core.model.Director;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreateDirectorUseCase {

    private final SaveDirectorBoundary saveDirectorBoundary;

    private final InvalidateDirectorsCacheBoundary invalidateDirectorsCacheBoundary;

    public Director execute(String name, String description, Long countryId) {
        try {
            log.info("Creating director with name: [{}]", name);

            Country country = Country.builder()
                    .id(countryId)
                    .build();

            Director director = Director.builder()
                    .name(name)
                    .description(description)
                    .country(country)
                    .build();

            Director savedDirector = saveDirectorBoundary.execute(director);

            invalidateDirectorsCacheBoundary.execute();

            return savedDirector;
        } catch (Exception e) {
            log.error("Error creating director", e);
            throw e;
        }
    }
}
