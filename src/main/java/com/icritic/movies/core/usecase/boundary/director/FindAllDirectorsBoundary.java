package com.icritic.movies.core.usecase.boundary.director;

import com.icritic.movies.core.model.Director;

import java.util.List;

public interface FindAllDirectorsBoundary {

    List<Director> execute();
}
