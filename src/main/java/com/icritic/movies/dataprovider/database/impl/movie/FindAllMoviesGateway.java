package com.icritic.movies.dataprovider.database.impl.movie;

import com.icritic.movies.core.model.Movie;
import com.icritic.movies.core.usecase.movie.FindAllMoviesBoundary;
import com.icritic.movies.dataprovider.database.entity.MovieEntity;
import com.icritic.movies.dataprovider.database.mapper.MovieEntityMapper;
import com.icritic.movies.dataprovider.database.repository.MovieEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FindAllMoviesGateway implements FindAllMoviesBoundary {

    private final MovieEntityRepository movieEntityRepository;

    public Page<Movie> execute(Pageable pageable) {
        MovieEntityMapper mapper = MovieEntityMapper.INSTANCE;

        Page<MovieEntity> pageableMovies = movieEntityRepository.findAllByActiveOrderByIdAsc(pageable, true);

        List<Movie> movies = pageableMovies.getContent()
                .stream()
                .map(mapper::movieEntityToMovie)
                .collect(Collectors.toList());

        return new PageImpl<>(movies, pageable, pageableMovies.getTotalElements());
    }
}
