package com.icritic.movies.dataprovider.database.mapper;

import com.icritic.movies.core.model.Director;
import com.icritic.movies.dataprovider.database.entity.DirectorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class DirectorEntityMapper {

    public static final DirectorEntityMapper INSTANCE = Mappers.getMapper(DirectorEntityMapper.class);

    @Mapping(target = "countryId", source = "country.id")
    public abstract DirectorEntity directorToDirectorEntity(Director director);

    @Mapping(target = "country.id", source = "countryId")
    public abstract Director directorEntityToDirector(DirectorEntity directorEntity);
}
