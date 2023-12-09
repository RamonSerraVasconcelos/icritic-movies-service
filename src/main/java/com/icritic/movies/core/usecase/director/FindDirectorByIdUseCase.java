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
public class FindDirectorByIdUseCase {

    private final FindDirectorByIdBoundary findDirectorByIdBoundary;

    public Director execute(Long id) {
        log.info("Finding director with id [{}]", id);

        Optional<Director> director = findDirectorByIdBoundary.execute(id);

        if (director.isEmpty())   {
            log.error("Director with id [{}] not found", id);
            throw new ResourceNotFoundException("Director not found");
        }

        return director.get();
    }
}
