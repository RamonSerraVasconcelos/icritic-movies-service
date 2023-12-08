package com.icritic.movies.dataprovider.database.impl.actor;

import com.icritic.movies.core.model.Actor;
import com.icritic.movies.core.usecase.actor.FindAllActorsBoundary;
import com.icritic.movies.dataprovider.database.mapper.ActorEntityMapper;
import com.icritic.movies.dataprovider.database.repository.ActorEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FindAllActorsGateway implements FindAllActorsBoundary {

    private final ActorEntityRepository actorEntityRepository;

    public List<Actor> execute() {
        ActorEntityMapper mapper = ActorEntityMapper.INSTANCE;

        return actorEntityRepository.findAllByOrderByIdAsc().stream().map(mapper::actorEntityToActor).collect(Collectors.toList());
    }
}
