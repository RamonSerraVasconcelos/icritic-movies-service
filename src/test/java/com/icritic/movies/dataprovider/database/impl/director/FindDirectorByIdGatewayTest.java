package com.icritic.movies.dataprovider.database.impl.director;

import com.icritic.movies.core.model.Director;
import com.icritic.movies.dataprovider.database.entity.DirectorEntity;
import com.icritic.movies.dataprovider.database.fixture.DirectorEntityFixture;
import com.icritic.movies.dataprovider.database.repository.DirectorEntityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindDirectorByIdGatewayTest {

    @InjectMocks
    private FindDirectorByIdGateway findDirectorByIdGateway;

    @Mock
    private DirectorEntityRepository repository;

    @Test
    void givenValidId_thenFind_andReturnDirector() {
        DirectorEntity directorEntity = DirectorEntityFixture.load();

        when(repository.findById(1L)).thenReturn(Optional.of(directorEntity));

        Optional<Director> director = findDirectorByIdGateway.execute(1L);

        assertThat(director).isPresent();
    }
}