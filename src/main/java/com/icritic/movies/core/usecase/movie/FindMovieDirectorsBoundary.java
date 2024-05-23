package com.icritic.movies.core.usecase.movie;

import com.icritic.movies.core.model.Director;

import java.util.List;

public interface FindMovieDirectorsBoundary {

    List<Director> execute(Long movieId);
}
