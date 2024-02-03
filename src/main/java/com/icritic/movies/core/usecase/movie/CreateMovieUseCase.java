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
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("DuplicatedCode")
@Component
@RequiredArgsConstructor
@Slf4j
public class CreateMovieUseCase {

    private final FindCategoryByIdBoundary findCategoryByIdBoundary;

    private final FindDirectorByIdBoundary findDirectorByIdBoundary;

    private final FindActorByIdBoundary findActorByIdBoundary;

    private final FindCountryByIdBoundary findCountryByIdBoundary;

    private final SaveMovieBoundary saveMovieBoundary;

    private final InvalidateMoviesCacheBoundary invalidateMoviesCacheBoundary;

    public Movie execute(MovieRequestParams movieRequestParams) {
        try {
            log.info("Creating movie with name: [{}]", movieRequestParams.getName());

            List<Category> categories = ProcessMovieEntitiesUtil.process(movieRequestParams.getCategories(), findCategoryByIdBoundary::execute, "Category");
            List<Director> directors = ProcessMovieEntitiesUtil.process(movieRequestParams.getDirectors(), findDirectorByIdBoundary::execute, "Director");
            List<Actor> actors = ProcessMovieEntitiesUtil.process(movieRequestParams.getActors(), findActorByIdBoundary::execute, "Actor");

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
                    .active(true)
                    .createdAt(LocalDateTime.now())
                    .build();

            Movie savedMovie = saveMovieBoundary.execute(movie);

            savedMovie.setCountry(country);
            invalidateMoviesCacheBoundary.execute();

            return savedMovie;
        } catch (Exception e) {
            log.error("Error creating movie", e);
            throw e;
        }
    }
}
