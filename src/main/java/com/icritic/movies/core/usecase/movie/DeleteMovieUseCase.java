package com.icritic.movies.core.usecase.movie;

import com.icritic.movies.core.model.Movie;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DeleteMovieUseCase {

    private final FindMovieByIdUseCase findMovieByIdUseCase;

    private final SaveMovieBoundary saveMovieBoundary;

    public void execute(Long id) {
        try {
            log.info("Deleting movie with id: [{}]", id);

            Movie movie = findMovieByIdUseCase.execute(id);

            movie.setActive(false);

            saveMovieBoundary.execute(movie);
        } catch (Exception e) {
            log.error("Error deleting movie with id: [{}]", id);
            throw e;
        }
    }
}
