package com.icritic.movies.dataprovider.database.impl.movie;

import com.icritic.movies.core.model.Movie;
import com.icritic.movies.core.usecase.movie.FindAllMoviesBoundary;
import com.icritic.movies.dataprovider.database.mapper.MovieEntityMapper;
import com.icritic.movies.dataprovider.database.repository.MovieEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FindAllMoviesGateway implements FindAllMoviesBoundary {

    private final MovieEntityRepository movieEntityRepository;

    public List<Movie> execute() {
        MovieEntityMapper mapper = MovieEntityMapper.INSTANCE;

        return movieEntityRepository.findAllByOrderByIdAsc().stream().map(mapper::movieEntityToMovie).collect(Collectors.toList());
    }
}
