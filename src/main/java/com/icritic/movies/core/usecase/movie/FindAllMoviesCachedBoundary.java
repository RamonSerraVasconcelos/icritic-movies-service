package com.icritic.movies.core.usecase.movie;

import com.icritic.movies.core.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FindAllMoviesCachedBoundary {

    Page<Movie> execute(Pageable pageable);
}
