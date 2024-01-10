package com.icritic.movies.dataprovider.database.impl.director;

import com.icritic.movies.core.model.Director;
import com.icritic.movies.dataprovider.database.entity.DirectorEntity;
import com.icritic.movies.dataprovider.database.repository.DirectorEntityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindAllDirectorsGatewayTest {

    @InjectMocks
    private FindAllDirectorsGateway findAllDirectorsGateway;

    @Mock
    private DirectorEntityRepository directorEntityRepository;

    @Mock
    private Pageable pageable;

    @Mock
    private Page<DirectorEntity> pageableDirector;

    @Test
    void givenExecution_thenReturnAllDirectors() {
        when(directorEntityRepository.findAllByOrderByIdAsc(pageable)).thenReturn(pageableDirector);

        Page<Director> directors = findAllDirectorsGateway.execute(pageable);

        assertThat(directors).isNotNull();
    }
}