package com.icritic.movies.core.usecase.movie;

import com.icritic.movies.core.model.Actor;
import com.icritic.movies.core.model.Country;
import com.icritic.movies.core.model.Director;
import com.icritic.movies.core.model.Movie;
import com.icritic.movies.core.usecase.country.FindCountryByIdBoundary;
import com.icritic.movies.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindMovieByIdUseCase {

    private final FindMovieByIdBoundary findMovieByIdBoundary;

    private final FindCountryByIdBoundary findCountryByIdBoundary;

    private final UpdateAverageMovieRatingUseCase updateAverageMovieRatingUseCase;

    private final SaveMovieToCacheBoundary saveMovieToCacheBoundary;

    private final FindMovieCachedBoundary findMovieCachedBoundary;

    public Movie execute(Long id) {
        try {
            log.info("Finding movie with id: {}", id);

            Movie cachedMovie = findMovieCachedBoundary.execute(id);

            if (nonNull(cachedMovie)) {
                return cachedMovie;
            }

            Optional<Movie> optionalMovie = findMovieByIdBoundary.execute(id);

            if (optionalMovie.isEmpty()) {
                throw new ResourceNotFoundException("Movie not found");
            }

            Movie movie = optionalMovie.get();

            if (!movie.isActive()) {
                throw new ResourceNotFoundException("Movie not found");
            }

            Optional<Country> optionalCountry = findCountryByIdBoundary.execute(movie.getCountry().getId());

            optionalCountry.ifPresent(movie::setCountry);

            setDirectorsCountries(movie.getDirectors());
            setActorsCountries(movie.getActors());

            int rating = updateAverageMovieRatingUseCase.execute(movie.getId());
            movie.setRating(rating);

            saveMovieToCacheBoundary.execute(movie);

            return movie;
        } catch (Exception e) {
            log.error("Error finding movie by id: {}", id, e);
            throw e;
        }
    }

    private void setDirectorsCountries(List<Director> directors) {
        directors.forEach(director -> {
            Optional<Country> optionalCountry = findCountryByIdBoundary.execute(director.getCountry().getId());

            optionalCountry.ifPresent(director::setCountry);
        });
    }

    private void setActorsCountries(List<Actor> actors) {
        actors.forEach(actor -> {
            Optional<Country> optionalCountry = findCountryByIdBoundary.execute(actor.getCountry().getId());

            optionalCountry.ifPresent(actor::setCountry);
        });
    }
}
