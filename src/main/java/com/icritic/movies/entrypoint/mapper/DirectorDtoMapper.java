package com.icritic.movies.entrypoint.mapper;

import com.icritic.movies.core.model.Director;
import com.icritic.movies.entrypoint.dto.director.DirectorResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class DirectorDtoMapper {

    public static DirectorDtoMapper INSTANCE = Mappers.getMapper(DirectorDtoMapper.class);

    @Mapping(target = "countryId", source = "country.id")
    public abstract DirectorResponseDto directorToDirectorResponseDto(Director director);
}
