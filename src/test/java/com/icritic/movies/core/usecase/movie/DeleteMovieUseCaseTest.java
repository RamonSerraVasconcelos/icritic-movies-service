package com.icritic.movies.core.usecase.movie;

import com.icritic.movies.core.model.Movie;
import com.icritic.movies.core.usecase.fixture.MovieFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeleteMovieUseCaseTest {

    @InjectMocks
    private DeleteMovieUseCase deleteMovieUseCase;

    @Mock
    private FindMovieByIdUseCase findMovieByIdUseCase;

    @Mock
    private SaveMovieBoundary saveMovieBoundary;

    @Captor
    private ArgumentCaptor<Movie> movieArgumentCaptor;

    @Test
    void givenValidParams_whenMovieIsFound_thenDelete() {
        when(findMovieByIdUseCase.execute(1L)).thenReturn(MovieFixture.load());

        deleteMovieUseCase.execute(1L);

        verify(saveMovieBoundary).execute(movieArgumentCaptor.capture());

        assertThat(movieArgumentCaptor.getValue().isActive()).isFalse();
    }
}