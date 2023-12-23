package com.icritic.movies.dataprovider.database.mapper;

import com.icritic.movies.core.model.Actor;
import com.icritic.movies.core.model.Director;
import com.icritic.movies.core.model.Movie;
import com.icritic.movies.dataprovider.database.entity.ActorEntity;
import com.icritic.movies.dataprovider.database.entity.DirectorEntity;
import com.icritic.movies.dataprovider.database.entity.MovieEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public abstract class MovieEntityMapper {

    public static final MovieEntityMapper INSTANCE = Mappers.getMapper(MovieEntityMapper.class);

    @Mapping(target = "countryId", source = "country.id")
    public abstract MovieEntity movieToMovieEntity(Movie movie);

    @Mapping(target = "country.id", source = "countryId")
    public abstract Movie movieEntityToMovie(MovieEntity movieEntity);

    @Mapping(target = "country.id", source = "countryId")
    protected abstract Director mapDirectorEntityToDirector(DirectorEntity directorEntity);

    protected abstract List<Actor> mapActorEntitiesToActors(List<ActorEntity> actorEntities);

    @Mapping(target = "country.id", source = "countryId")
    protected abstract Actor mapActorEntityToActor(ActorEntity actorEntity);
}
