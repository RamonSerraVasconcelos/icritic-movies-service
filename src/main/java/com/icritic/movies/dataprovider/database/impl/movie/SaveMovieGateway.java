package com.icritic.movies.dataprovider.database.impl.movie;

import com.icritic.movies.core.model.Movie;
import com.icritic.movies.core.usecase.movie.SaveMovieBoundary;
import com.icritic.movies.dataprovider.database.entity.MovieEntity;
import com.icritic.movies.dataprovider.database.mapper.MovieEntityMapper;
import com.icritic.movies.dataprovider.database.repository.MovieEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SaveMovieGateway implements SaveMovieBoundary {

    private final MovieEntityRepository movieEntityRepository;

    public Movie execute(Movie movie) {
        MovieEntityMapper mapper = MovieEntityMapper.INSTANCE;

        MovieEntity savedMovie = movieEntityRepository.save(mapper.movieToMovieEntity(movie));

        return mapper.movieEntityToMovie(savedMovie);
    }
}
