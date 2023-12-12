package com.icritic.movies.entrypoint.mapper;

import com.icritic.movies.core.model.Actor;
import com.icritic.movies.entrypoint.dto.actor.ActorResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class ActorDtoMapper {

    public static ActorDtoMapper INSTANCE = Mappers.getMapper(ActorDtoMapper.class);

    public abstract ActorResponseDto actorToActorResponseDto(Actor actor);
}
