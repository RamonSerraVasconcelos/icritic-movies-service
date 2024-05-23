package com.icritic.movies.dataprovider.database.impl.movie;

import com.icritic.movies.core.model.Director;
import com.icritic.movies.dataprovider.database.entity.DirectorEntity;
import com.icritic.movies.dataprovider.database.fixture.DirectorEntityFixture;
import com.icritic.movies.dataprovider.database.repository.DirectorEntityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindMovieDirectorsGatewayTest {

    @InjectMocks
    private FindMovieDirectorsGateway findMovieDirectorsGateway;

    @Mock
    private DirectorEntityRepository directorEntityRepository;

    @Test
    void givenExecution_returnDirectors() {
        List<DirectorEntity> directorEntities = List.of(DirectorEntityFixture.load(), DirectorEntityFixture.load());

        when(directorEntityRepository.findMovieCategories(1L)).thenReturn(directorEntities);

        List<Director> directors = findMovieDirectorsGateway.execute(1L);

        verify(directorEntityRepository).findMovieCategories(1L);
        assertThat(directors).usingRecursiveComparison().ignoringFields("country").isEqualTo(directorEntities);
    }
}