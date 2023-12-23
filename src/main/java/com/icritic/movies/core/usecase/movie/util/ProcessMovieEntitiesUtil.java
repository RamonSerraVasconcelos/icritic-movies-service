package com.icritic.movies.core.usecase.movie.util;

import com.icritic.movies.exception.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class ProcessMovieEntitiesUtil {

    public static <T> List<T> process(List<Long> ids, Function<Long, Optional<T>> findFunction, String entityName) {
        List<T> results = new ArrayList<>();

        ids.forEach(id -> {
            T entity = findFunction.apply(id).orElseThrow(() -> new ResourceNotFoundException(entityName + " with id: " + id + " not found"));
            results.add(entity);
        });

        return results;
    }
}
