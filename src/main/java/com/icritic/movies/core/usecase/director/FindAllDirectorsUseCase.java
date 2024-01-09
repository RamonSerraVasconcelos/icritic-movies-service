package com.icritic.movies.core.usecase.director;

import com.icritic.movies.core.model.Director;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class FindAllDirectorsUseCase {

    private final FindAllDirectorsBoundary findAllDirectorsBoundary;

    public Page<Director> execute(Pageable pageable) {
        log.info("Finding all directors");
        return findAllDirectorsBoundary.execute(pageable);
    }
}
