package com.icritic.movies.core.usecase.movie;

import com.icritic.movies.core.model.Movie;
import org.springframework.data.domain.Page;

public interface SaveMoviesToCacheBoundary {

    void execute(Page<Movie> movies);
}
