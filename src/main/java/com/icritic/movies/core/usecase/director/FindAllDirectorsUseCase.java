package com.icritic.movies.core.usecase.director;

import com.icritic.movies.core.model.Director;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class FindAllDirectorsUseCase {

    private final FindAllDirectorsBoundary findAllDirectorsBoundary;

    public List<Director> execute() {
        log.info("Finding all directors");
        return findAllDirectorsBoundary.execute();
    }
}
