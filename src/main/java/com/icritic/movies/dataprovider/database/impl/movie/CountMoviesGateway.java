package com.icritic.movies.dataprovider.database.impl.movie;

import com.icritic.movies.core.model.MovieFilter;
import com.icritic.movies.core.usecase.movie.CountMoviesBoundary;
import com.icritic.movies.dataprovider.database.repository.MovieEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CountMoviesGateway implements CountMoviesBoundary {

    private final MovieEntityRepository movieEntityRepository;

    public Long execute(MovieFilter movieFilter) {
        return movieEntityRepository.countMovies(movieFilter);
    }
}
