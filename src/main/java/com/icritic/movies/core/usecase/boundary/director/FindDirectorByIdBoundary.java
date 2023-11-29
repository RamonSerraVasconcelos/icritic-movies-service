package com.icritic.movies.core.usecase.boundary.director;

import com.icritic.movies.core.model.Director;

import java.util.Optional;

public interface FindDirectorByIdBoundary {

    Optional<Director> execute(Long id);
}
