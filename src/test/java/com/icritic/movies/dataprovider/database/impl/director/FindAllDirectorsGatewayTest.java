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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindAllDirectorsGatewayTest {

    @InjectMocks
    private FindAllDirectorsGateway findAllDirectorsGateway;

    @Mock
    private DirectorEntityRepository directorEntityRepository;

    @Test
    void givenExecution_thenReturnAllDirectors() {
        List< DirectorEntity> directorsEntities = List.of(DirectorEntityFixture.load(), DirectorEntityFixture.load());

        when(directorEntityRepository.findAllByOrderByIdAsc()).thenReturn(directorsEntities);

        List<Director> directors = findAllDirectorsGateway.execute();

        assertThat(directors).isNotNull().hasSize(2);
    }
}