package com.icritic.movies.entrypoint.mapper;

import com.icritic.movies.core.model.Actor;
import com.icritic.movies.entrypoint.dto.actor.ActorResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class ActorDtoMapper {

    public static ActorDtoMapper INSTANCE = Mappers.getMapper(ActorDtoMapper.class);

    @Mapping(target = "countryId", source = "country.id")
    public abstract ActorResponseDto actorToActorResponseDto(Actor actor);
}
