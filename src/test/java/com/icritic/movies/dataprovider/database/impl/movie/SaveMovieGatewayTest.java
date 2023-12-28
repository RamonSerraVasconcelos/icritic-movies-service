package com.icritic.movies.dataprovider.database.impl.movie;

import com.icritic.movies.core.model.Movie;
import com.icritic.movies.core.usecase.fixture.MovieFixture;
import com.icritic.movies.dataprovider.database.entity.MovieEntity;
import com.icritic.movies.dataprovider.database.fixture.MovieEntityFixture;
import com.icritic.movies.dataprovider.database.repository.MovieEntityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SaveMovieGatewayTest {

    @InjectMocks
    private SaveMovieGateway saveMovieGateway;

    @Mock
    private MovieEntityRepository movieEntityRepository;

    @Test
    void givenValidMovie_thenSave_andReturnMovie() {
        Movie movie = MovieFixture.load();
        MovieEntity movieEntity = MovieEntityFixture.load();

        when(movieEntityRepository.save(any(MovieEntity.class))).thenReturn(movieEntity);

        Movie savedMovie = saveMovieGateway.execute(movie);

        verify(movieEntityRepository).save(any(MovieEntity.class));

        assertThat(savedMovie).isNotNull();
    }
}