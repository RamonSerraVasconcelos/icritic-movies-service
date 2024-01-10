package com.icritic.movies.dataprovider.database.impl.actor;

import com.icritic.movies.core.model.Actor;
import com.icritic.movies.dataprovider.database.entity.ActorEntity;
import com.icritic.movies.dataprovider.database.repository.ActorEntityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindAllActorsGatewayTest {

    @InjectMocks
    private FindAllActorsGateway findAllActorsGateway;

    @Mock
    private ActorEntityRepository actorEntityRepository;

    @Mock
    private Pageable pageable;

    @Mock
    private Page<ActorEntity> pageableActor;

    @Test
    void givenExecution_thenReturnActors() {
        when(actorEntityRepository.findAllByOrderByIdAsc(pageable)).thenReturn(pageableActor);

        Page<Actor> actors = findAllActorsGateway.execute(pageable);

        verify(actorEntityRepository).findAllByOrderByIdAsc(pageable);
        assertThat(actors).isNotNull();
    }
}