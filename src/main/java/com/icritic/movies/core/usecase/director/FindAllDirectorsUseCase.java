package com.icritic.movies.core.usecase.director;

import com.icritic.movies.core.model.Director;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import static java.util.Objects.nonNull;

@Component
@RequiredArgsConstructor
@Slf4j
public class FindAllDirectorsUseCase {

    private final FindAllDirectorsBoundary findAllDirectorsBoundary;

    private final FindAllDirectorsCachedBoundary findAllDirectorsCachedBoundary;

    private final SaveDirectorsToCacheBoundary saveDirectorsToCacheBoundary;

    public Page<Director> execute(Pageable pageable) {
        log.info("Finding all directors");

        Page<Director> cachedDirectors = findAllDirectorsCachedBoundary.execute(pageable);

        if (nonNull(cachedDirectors)) {
            return cachedDirectors;
        }

        Page<Director> directors = findAllDirectorsBoundary.execute(pageable);

        saveDirectorsToCacheBoundary.execute(directors);

        return directors;
    }
}
