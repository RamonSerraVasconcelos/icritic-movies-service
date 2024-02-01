package com.icritic.movies.core.usecase.director;

import com.icritic.movies.core.model.Director;
import com.icritic.movies.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class UpdateDirectorUseCase {

    private final FindDirectorByIdBoundary findDirectorByIdBoundary;

    private final SaveDirectorBoundary saveDirectorBoundary;

    private final InvalidateDirectorsCacheBoundary invalidateDirectorsCacheBoundary;

    public Director execute(Long id, String name, String description, Long countryId) {
        Optional<Director> directorOptional = findDirectorByIdBoundary.execute(id);

        if (directorOptional.isEmpty()) {
            throw new ResourceNotFoundException("Director not found");
        }

        Director director = directorOptional.get();

        director.setName(name);
        director.setDescription(description);
        director.getCountry().setId(countryId);

        Director updatedDirector = saveDirectorBoundary.execute(director);

        invalidateDirectorsCacheBoundary.execute();

        return updatedDirector;
    }
}
