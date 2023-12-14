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
import com.icritic.movies.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreateMovieUseCase {

    private final FindCategoryByIdBoundary findCategoryByIdBoundary;

    private final FindDirectorByIdBoundary findDirectorByIdBoundary;

    private final FindActorByIdBoundary findActorByIdBoundary;

    private final FindCountryByIdBoundary findCountryByIdBoundary;

    private final SaveMovieBoundary saveMovieBoundary;

    public Movie execute(MovieRequestParams movieRequestParams) {
        try {
            List<Category> categories = new ArrayList<>();
            List<Director> directors = new ArrayList<>();
            List<Actor> actors = new ArrayList<>();

            processMovieEntitiesids(movieRequestParams.getCategories(), findCategoryByIdBoundary::execute, categories, "Category");
            processMovieEntitiesids(movieRequestParams.getDirectors(), findDirectorByIdBoundary::execute, directors, "Director");
            processMovieEntitiesids(movieRequestParams.getActors(), findActorByIdBoundary::execute, actors, "Actor");

            Optional<Country> optionalCountry = findCountryByIdBoundary.execute(movieRequestParams.getCountryId());

            if (optionalCountry.isEmpty()) {
                throw new ResourceNotFoundException("Country not found");
            }

            Country country = optionalCountry.get();

            Movie movie = Movie.builder()
                    .name(movieRequestParams.getName())
                    .synopsis(movieRequestParams.getSynopsis())
                    .categories(categories)
                    .directors(directors)
                    .actors(actors)
                    .country(country)
                    .releaseDate(movieRequestParams.getReleaseDate())
                    .rating(0L)
                    .build();

            Movie savedMovie = saveMovieBoundary.execute(movie);

            savedMovie.setCountry(country);

            return savedMovie;
        } catch (Exception e) {
            log.error("Error creating movie", e);
            throw e;
        }
    }

    private <T> void processMovieEntitiesids(List<Long> ids, Function<Long, Optional<T>> findFunction, List<T> result, String entityName) {
        ids.forEach(id -> {
            T entity = findFunction.apply(id).orElseThrow(() -> new ResourceNotFoundException(entityName + " with id: " + id + " not found"));
            result.add(entity);
        });
    }
}
