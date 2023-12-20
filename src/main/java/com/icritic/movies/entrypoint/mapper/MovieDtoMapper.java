package com.icritic.movies.entrypoint.mapper;

import com.icritic.movies.core.model.Movie;
import com.icritic.movies.core.model.MovieRequestParams;
import com.icritic.movies.entrypoint.dto.movie.MovieRequestDto;
import com.icritic.movies.entrypoint.dto.movie.MovieResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class MovieDtoMapper {

    public static final MovieDtoMapper INSTANCE = Mappers.getMapper(MovieDtoMapper.class);

    public abstract MovieRequestParams movieRequestDtoToMovieRequestParams(MovieRequestDto movieRequestDto);

    public abstract MovieResponseDto movieToMovieResponseDto(Movie movie);
}
