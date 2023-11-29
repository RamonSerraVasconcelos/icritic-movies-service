package com.icritic.movies.core.usecase.boundary.director;

import com.icritic.movies.core.model.Director;

public interface SaveDirectorBoundary {

    Director execute(Director director);
}
