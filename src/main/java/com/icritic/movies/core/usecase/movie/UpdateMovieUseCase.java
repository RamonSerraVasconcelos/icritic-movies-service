package com.icritic.movies.core.usecase.movie;

import com.icritic.movies.core.model.Actor;
import com.icritic.movies.core.model.Category;
import com.icritic.movies.core.model.Country;
import com.icritic.movies.core.model.Director;
import com.icritic.movies.core.model.Movie;
import com.icritic.movies.core.model.MovieRequestParams;
import com.icritic.movies.core.usecase.actor.FindActorByIdBoundary;
import com.icritic.movies.core.usecase.category.FindCategoryByIdBoundary;
import com.icritic.movies.core.usecase.country.FindCountryByIdBoundary;
import com.icritic.movies.core.usecase.director.FindDirectorByIdBoundary;
import com.icritic.movies.core.usecase.movie.util.ProcessMovieEntitiesUtil;
import com.icritic.movies.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("DuplicatedCode")
@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateMovieUseCase {

    private final FindMovieByIdUseCase findMovieByIduseCase;

    private final FindCategoryByIdBoundary findCategoryByIdBoundary;

    private final FindDirectorByIdBoundary findDirectorByIdBoundary;

    private final FindActorByIdBoundary findActorByIdBoundary;

    private final FindCountryByIdBoundary findCountryByIdBoundary;

    private final SaveMovieBoundary saveMovieBoundary;

    private final InvalidateMoviesCacheBoundary invalidateMoviesCacheBoundary;

    public Movie execute(Long id, MovieRequestParams movieRequestParams) {
        try {
            log.info("Updating movie with id: [{}]", id);

            Movie movie = findMovieByIduseCase.execute(id);

            List<Category> categories = ProcessMovieEntitiesUtil.process(movieRequestParams.getCategories(), findCategoryByIdBoundary::execute, "Category");
            List<Director> directors = ProcessMovieEntitiesUtil.process(movieRequestParams.getDirectors(), findDirectorByIdBoundary::execute, "Director");
            List<Actor> actors = ProcessMovieEntitiesUtil.process(movieRequestParams.getActors(), findActorByIdBoundary::execute, "Actor");

            Optional<Country> optionalCountry = findCountryByIdBoundary.execute(movieRequestParams.getCountryId());

            if (optionalCountry.isEmpty()) {
                throw new ResourceNotFoundException("Country not found");
            }

            movie.setName(movieRequestParams.getName());
            movie.setSynopsis(movieRequestParams.getSynopsis());
            movie.setCategories(categories);
            movie.setDirectors(directors);
            movie.setActors(actors);
            movie.setCountry(optionalCountry.get());
            movie.setReleaseDate(movieRequestParams.getReleaseDate());

            Movie updatedMovie = saveMovieBoundary.execute(movie);

            invalidateMoviesCacheBoundary.execute();

            return updatedMovie;
        } catch (Exception e) {
            log.error("Error updating movie with id: [{}]", id, e);
            throw e;
        }
    }
}
