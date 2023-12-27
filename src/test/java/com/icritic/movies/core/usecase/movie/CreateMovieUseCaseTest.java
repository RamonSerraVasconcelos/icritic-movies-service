package com.icritic.movies.core.usecase.movie;

import com.icritic.movies.core.model.Movie;
import com.icritic.movies.core.model.MovieRequestParams;
import com.icritic.movies.core.usecase.actor.FindActorByIdBoundary;
import com.icritic.movies.core.usecase.category.FindCategoryByIdBoundary;
import com.icritic.movies.core.usecase.country.FindCountryByIdBoundary;
import com.icritic.movies.core.usecase.director.FindDirectorByIdBoundary;
import com.icritic.movies.core.usecase.fixture.ActorFixture;
import com.icritic.movies.core.usecase.fixture.CategoryFixture;
import com.icritic.movies.core.usecase.fixture.CountryFixture;
import com.icritic.movies.core.usecase.fixture.DirectorFixture;
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
class CreateMovieUseCaseTest {

    @InjectMocks
    private CreateMovieUseCase createMovieUseCase;

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
    void givenValidParams_whenMoviesEntitiesAreFound_thenCreate_andReturnMovie() {
        MovieRequestParams movieRequestParams = MovieRequestParamsFixture.load();

        when(findCategoryByIdBoundary.execute(movieRequestParams.getCategories().get(0))).thenReturn(Optional.of(CategoryFixture.load()));
        when(findDirectorByIdBoundary.execute(movieRequestParams.getDirectors().get(0))).thenReturn(Optional.of(DirectorFixture.load()));
        when(findActorByIdBoundary.execute(movieRequestParams.getActors().get(0))).thenReturn(Optional.of(ActorFixture.load()));
        when(findCountryByIdBoundary.execute(movieRequestParams.getCountryId())).thenReturn(Optional.of(CountryFixture.load()));
        when(saveMovieBoundary.execute(any(Movie.class))).thenReturn(MovieFixture.load());

        Movie movie = createMovieUseCase.execute(movieRequestParams);

        verify(findCategoryByIdBoundary).execute(anyLong());
        verify(findDirectorByIdBoundary).execute(anyLong());
        verify(findActorByIdBoundary).execute(anyLong());
        verify(findCountryByIdBoundary).execute(anyLong());
        verify(saveMovieBoundary).execute(any(Movie.class));

        assertThat(movie).isNotNull();
        assertThat(movie.getName()).isEqualTo(movieRequestParams.getName());
    }

    @Test
    void givenValidParams_whenMoviesEntitiesAreNotFound_thenThrowResourceNotFoundException() {
        MovieRequestParams movieRequestParams = MovieRequestParamsFixture.load();

        when(findCategoryByIdBoundary.execute(movieRequestParams.getCategories().get(0))).thenReturn(Optional.of(CategoryFixture.load()));
        when(findDirectorByIdBoundary.execute(movieRequestParams.getDirectors().get(0))).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> createMovieUseCase.execute(movieRequestParams));
    }

    @Test
    void givenValidParams_whenCountrIsNotFound_thenThrowResourceNotFoundException() {
        MovieRequestParams movieRequestParams = MovieRequestParamsFixture.load();

        when(findCategoryByIdBoundary.execute(movieRequestParams.getCategories().get(0))).thenReturn(Optional.of(CategoryFixture.load()));
        when(findDirectorByIdBoundary.execute(movieRequestParams.getDirectors().get(0))).thenReturn(Optional.of(DirectorFixture.load()));
        when(findActorByIdBoundary.execute(movieRequestParams.getActors().get(0))).thenReturn(Optional.of(ActorFixture.load()));
        when(findCountryByIdBoundary.execute(movieRequestParams.getCountryId())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> createMovieUseCase.execute(movieRequestParams));

        verifyNoInteractions(saveMovieBoundary);
    }
}