package com.icritic.movies.core.usecase.actor;

import com.icritic.movies.core.model.Actor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class FindAllActorsUseCase {

    private final FindAllActorsBoundary findAllActorsBoundary;

    public Page<Actor> execute(Pageable pageable) {
        log.info("Loading all actors");
        return findAllActorsBoundary.execute(pageable);
    }
}
