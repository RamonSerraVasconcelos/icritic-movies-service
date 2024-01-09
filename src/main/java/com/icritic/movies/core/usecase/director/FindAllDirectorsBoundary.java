package com.icritic.movies.core.usecase.director;

import com.icritic.movies.core.model.Director;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FindAllDirectorsBoundary {

    Page<Director> execute(Pageable pageable);
}
