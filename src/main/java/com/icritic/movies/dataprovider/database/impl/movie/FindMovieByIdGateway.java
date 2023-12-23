package com.icritic.movies.dataprovider.database.impl.movie;

import com.icritic.movies.core.model.Movie;
import com.icritic.movies.core.usecase.movie.FindMovieByIdBoundary;
import com.icritic.movies.dataprovider.database.mapper.MovieEntityMapper;
import com.icritic.movies.dataprovider.database.repository.MovieEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FindMovieByIdGateway implements FindMovieByIdBoundary {

    private final MovieEntityRepository movieEntityRepository;

    public Optional<Movie> execute(Long id) {
        MovieEntityMapper mapper = MovieEntityMapper.INSTANCE;

        return movieEntityRepository.findById(id).map(mapper::movieEntityToMovie);
    }
}
