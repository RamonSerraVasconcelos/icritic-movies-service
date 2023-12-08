package com.icritic.movies.dataprovider.database.mapper;

import com.icritic.movies.core.model.Actor;
import com.icritic.movies.dataprovider.database.entity.ActorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class ActorEntityMapper {

    public static final ActorEntityMapper INSTANCE = Mappers.getMapper(ActorEntityMapper.class);

    @Mapping(target = "countryId", source = "country.id")
    public abstract ActorEntity actorToActorEntity(Actor actor);

    @Mapping(target = "country.id", source = "countryId")
    public abstract Actor actorEntityToActor(ActorEntity actorEntity);
}
