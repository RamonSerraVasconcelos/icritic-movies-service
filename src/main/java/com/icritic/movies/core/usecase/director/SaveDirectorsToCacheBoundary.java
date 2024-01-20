package com.icritic.movies.core.usecase.director;

import com.icritic.movies.core.model.Director;
import org.springframework.data.domain.Page;

public interface SaveDirectorsToCacheBoundary {

    void execute(Page<Director> directors);
}
