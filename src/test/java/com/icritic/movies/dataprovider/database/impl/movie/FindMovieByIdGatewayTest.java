package com.icritic.movies.dataprovider.database.impl.movie;

import com.icritic.movies.core.model.Movie;
import com.icritic.movies.dataprovider.database.entity.MovieEntity;
import com.icritic.movies.dataprovider.database.fixture.MovieEntityFixture;
import com.icritic.movies.dataprovider.database.repository.MovieEntityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindMovieByIdGatewayTest {

    @InjectMocks
    private FindMovieByIdGateway findMovieByIdGateway;

    @Mock
    private MovieEntityRepository movieEntityRepository;

    @Test
    void givenValidId_thenReturnMovie() {
        MovieEntity movieEntity = MovieEntityFixture.load();

        when(movieEntityRepository.findById(movieEntity.getId())).thenReturn(Optional.of(movieEntity));

        Optional<Movie> movie = findMovieByIdGateway.execute(movieEntity.getId());

        verify(movieEntityRepository).findById(movieEntity.getId());

        assertThat(movie).isPresent();
    }
}