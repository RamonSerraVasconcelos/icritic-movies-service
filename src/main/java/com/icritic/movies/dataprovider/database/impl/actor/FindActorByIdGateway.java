package com.icritic.movies.dataprovider.database.impl.actor;

import com.icritic.movies.core.model.Actor;
import com.icritic.movies.core.usecase.actor.FindActorByIdBoundary;
import com.icritic.movies.dataprovider.database.mapper.ActorEntityMapper;
import com.icritic.movies.dataprovider.database.repository.ActorEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FindActorByIdGateway implements FindActorByIdBoundary {

    private final ActorEntityRepository actorEntityRepository;

    public Optional<Actor> execute(Long id) {
        return actorEntityRepository.findById(id).map(ActorEntityMapper.INSTANCE::actorEntityToActor);
    }
}
