package com.icritic.movies.dataprovider.database.impl.actor;

import com.icritic.movies.core.model.Actor;
import com.icritic.movies.core.usecase.actor.SaveActorBoundary;
import com.icritic.movies.dataprovider.database.entity.ActorEntity;
import com.icritic.movies.dataprovider.database.mapper.ActorEntityMapper;
import com.icritic.movies.dataprovider.database.repository.ActorEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SaveActorGateway implements SaveActorBoundary {

    private final ActorEntityRepository actorEntityRepository;

    public Actor execute(Actor actor) {
        ActorEntityMapper mapper = ActorEntityMapper.INSTANCE;

        ActorEntity savedActor = actorEntityRepository.save(mapper.actorToActorEntity(actor));

        return mapper.actorEntityToActor(savedActor);
    }
}
