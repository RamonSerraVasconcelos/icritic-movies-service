package com.icritic.movies.core.usecase.director;

import com.icritic.movies.core.model.Country;
import com.icritic.movies.core.model.Director;
import com.icritic.movies.core.usecase.country.FindCountryByIdBoundary;
import com.icritic.movies.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class FindDirectorByIdUseCase {

    private final FindDirectorByIdBoundary findDirectorByIdBoundary;

    private final FindCountryByIdBoundary findCountryByIdBoundary;

    public Director execute(Long id) {
        log.info("Finding director with id [{}]", id);

        Optional<Director> optionalDirector = findDirectorByIdBoundary.execute(id);

        if (optionalDirector.isEmpty())   {
            log.error("Director with id [{}] not found", id);
            throw new ResourceNotFoundException("Director not found");
        }

        Director director = optionalDirector.get();

        Optional<Country> optionalCountry = findCountryByIdBoundary.execute(director.getCountry().getId());

        optionalCountry.ifPresent(director::setCountry);

        return director;
    }
}
