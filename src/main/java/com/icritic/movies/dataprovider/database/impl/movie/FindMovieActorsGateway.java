package com.icritic.movies.dataprovider.database.impl.movie;

import com.icritic.movies.core.model.Actor;
import com.icritic.movies.core.usecase.movie.FindMovieActorsBoundary;
import com.icritic.movies.dataprovider.database.entity.ActorEntity;
import com.icritic.movies.dataprovider.database.mapper.ActorEntityMapper;
import com.icritic.movies.dataprovider.database.repository.ActorEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FindMovieActorsGateway implements FindMovieActorsBoundary {

    private final ActorEntityRepository actorEntityRepository;

    @Override
    public List<Actor> execute(Long movieId) {
        ActorEntityMapper mapper = ActorEntityMapper.INSTANCE;

        List<ActorEntity> actorEntities = actorEntityRepository.findByMovieId(movieId);
        return actorEntities
                .stream()
                .map(mapper::actorEntityToActor)
                .collect(Collectors.toList());
    }
}
