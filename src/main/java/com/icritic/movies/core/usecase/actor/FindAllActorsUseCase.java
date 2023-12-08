package com.icritic.movies.core.usecase.actor;

import com.icritic.movies.core.model.Actor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class FindAllActorsUseCase {

    private final FindAllActorsBoundary findAllActorsBoundary;

    public List<Actor> execute() {
        log.info("Loading all actors");
        return findAllActorsBoundary.execute();
    }
}
