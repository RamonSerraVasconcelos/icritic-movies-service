package com.icritic.movies.dataprovider.database.impl.director;

import com.icritic.movies.core.model.Director;
import com.icritic.movies.core.usecase.fixture.DirectorFixture;
import com.icritic.movies.dataprovider.database.entity.DirectorEntity;
import com.icritic.movies.dataprovider.database.fixture.DirectorEntityFixture;
import com.icritic.movies.dataprovider.database.repository.DirectorEntityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SaveDirectorGatewayTest {

    @InjectMocks
    private SaveDirectorGateway saveDirectorGateway;

    @Mock
    private DirectorEntityRepository directorEntityRepository;

    @Test
    void givenValidDirector_whenExecute_thenSave_andReturnDirector() {
        Director director = DirectorFixture.load();
        DirectorEntity directorEntity = DirectorEntityFixture.load();

        when(directorEntityRepository.save(any(DirectorEntity.class))).thenReturn(directorEntity);

        Director returnedDirector = saveDirectorGateway.execute(director);

        assertThat(returnedDirector).isNotNull();
    }
}