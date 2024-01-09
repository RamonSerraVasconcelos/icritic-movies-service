package com.icritic.movies.core.usecase.actor;

import com.icritic.movies.core.model.Actor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FindAllActorsBoundary {

    Page<Actor> execute(Pageable pageable);
}
