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

            return saveDirectorBoundary.execute(director);
        } catch (Exception e) {
            log.error("Error creating director", e);
            throw e;
        }
    }
}
