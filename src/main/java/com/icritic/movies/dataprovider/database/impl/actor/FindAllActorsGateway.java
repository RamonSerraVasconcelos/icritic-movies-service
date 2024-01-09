package com.icritic.movies.dataprovider.database.impl.actor;

import com.icritic.movies.core.model.Actor;
import com.icritic.movies.core.usecase.actor.FindAllActorsBoundary;
import com.icritic.movies.dataprovider.database.entity.ActorEntity;
import com.icritic.movies.dataprovider.database.mapper.ActorEntityMapper;
import com.icritic.movies.dataprovider.database.repository.ActorEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FindAllActorsGateway implements FindAllActorsBoundary {

    private final ActorEntityRepository actorEntityRepository;

    public Page<Actor> execute(Pageable pageable) {
        ActorEntityMapper mapper = ActorEntityMapper.INSTANCE;

        Page<ActorEntity> pageableActors = actorEntityRepository.findAllByOrderByIdAsc(pageable);

        List<Actor> actorEntities = pageableActors.getContent()
                .stream()
                .map(mapper::actorEntityToActor)
                .collect(Collectors.toList());

        return new PageImpl<>(actorEntities, pageable, pageableActors.getTotalElements());
    }
}
