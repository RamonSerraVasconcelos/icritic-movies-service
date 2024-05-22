package com.icritic.movies.core.usecase.movie;

import com.icritic.movies.core.model.Movie;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FindAllMoviesCachedBoundary {

    List<Movie> execute(Pageable pageable);
}
