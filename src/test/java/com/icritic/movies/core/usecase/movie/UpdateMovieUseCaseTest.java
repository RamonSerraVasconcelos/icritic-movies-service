package com.icritic.movies.core.usecase.movie;

import com.icritic.movies.core.model.Movie;
import com.icritic.movies.core.model.MovieRequestParams;
import com.icritic.movies.core.usecase.actor.FindActorByIdBoundary;
import com.icritic.movies.core.usecase.category.FindCategoryByIdBoundary;
import com.icritic.movies.core.usecase.country.FindCountryByIdBoundary;
import com.icritic.movies.core.usecase.director.FindDirectorByIdBoundary;
import com.icritic.movies.core.usecase.fixture.MovieFixture;
import com.icritic.movies.core.usecase.fixture.MovieRequestParamsFixture;
import com.icritic.movies.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateMovieUseCaseTest {

    @InjectMocks
    private UpdateMovieUseCase updateMovieUseCase;

    @Mock
    private FindMovieByIdBoundary findMovieByIdBoundary;

    @Mock
    private FindCategoryByIdBoundary findCategoryByIdBoundary;

    @Mock
    private FindDirectorByIdBoundary findDirectorByIdBoundary;

    @Mock
    private FindActorByIdBoundary findActorByIdBoundary;

    @Mock
    private FindCountryByIdBoundary findCountryByIdBoundary;

    @Mock
    private SaveMovieBoundary saveMovieBoundary;

    @Test
    void givenValidParameters_whenMovieAndEntitiesAreFound_thenUpdate_andReturnMovie() {
        MovieRequestParams movieRequestParams = MovieRequestParamsFixture.load();
        Movie movie = MovieFixture.load();

        when(findMovieByIdBoundary.execute(anyLong())).thenReturn(Optional.of(movie));
        when(findCategoryByIdBoundary.execute(movieRequestParams.getCategories().get(0))).thenReturn(Optional.of(movie.getCategories().get(0)));
        when(findDirectorByIdBoundary.execute(movieRequestParams.getDirectors().get(0))).thenReturn(Optional.of(movie.getDirectors().get(0)));
        when(findActorByIdBoundary.execute(movieRequestParams.getActors().get(0))).thenReturn(Optional.of(movie.getActors().get(0)));
        when(findCountryByIdBoundary.execute(movieRequestParams.getCountryId())).thenReturn(Optional.of(movie.getCountry()));
        when(saveMovieBoundary.execute(movie)).thenReturn(movie);

        Movie updatedMovie = updateMovieUseCase.execute(1L, movieRequestParams);

        verify(findMovieByIdBoundary).execute(anyLong());
        verify(findCategoryByIdBoundary).execute(anyLong());
        verify(findDirectorByIdBoundary).execute(anyLong());
        verify(findActorByIdBoundary).execute(anyLong());
        verify(findCountryByIdBoundary).execute(anyLong());
        verify(saveMovieBoundary).execute(any(Movie.class));

        assertThat(updatedMovie).isNotNull();
    }

    @Test
    void givenValidParameters_movieIsNotFound_thenThrowResourceNotFoundException() {
        MovieRequestParams movieRequestParams = MovieRequestParamsFixture.load();

        when(findMovieByIdBoundary.execute(anyLong())).thenReturn(Optional.empty());

        verifyNoInteractions(findCategoryByIdBoundary);
        verifyNoInteractions(findDirectorByIdBoundary);
        verifyNoInteractions(findActorByIdBoundary);
        verifyNoInteractions(findCountryByIdBoundary);
        verifyNoInteractions(saveMovieBoundary);

        assertThrows(ResourceNotFoundException.class, () -> updateMovieUseCase.execute(1L, movieRequestParams));
    }

    @Test
    void givenValidParameters_whenMovieEntitiesAreNotFound_thenThrowResourceNotFoundException() {
        MovieRequestParams movieRequestParams = MovieRequestParamsFixture.load();

        when(findMovieByIdBoundary.execute(anyLong())).thenReturn(Optional.of(MovieFixture.load()));
        when(findCategoryByIdBoundary.execute(movieRequestParams.getCategories().get(0))).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> updateMovieUseCase.execute(1L, movieRequestParams));
    }
}