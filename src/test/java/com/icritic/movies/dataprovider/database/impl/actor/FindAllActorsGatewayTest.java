package com.icritic.movies.dataprovider.database.impl.actor;

import com.icritic.movies.core.model.Actor;
import com.icritic.movies.dataprovider.database.entity.ActorEntity;
import com.icritic.movies.dataprovider.database.fixture.ActorEntityFixture;
import com.icritic.movies.dataprovider.database.repository.ActorEntityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindAllActorsGatewayTest {

    @InjectMocks
    private FindAllActorsGateway findAllActorsGateway;

    @Mock
    private ActorEntityRepository actorEntityRepository;

    @Test
    void givenExecution_thenReturnActors() {
        List<ActorEntity> actorEntities = List.of(ActorEntityFixture.load(), ActorEntityFixture.load());

        when(actorEntityRepository.findAllByOrderByIdAsc()).thenReturn(actorEntities);

        List<Actor> actors = findAllActorsGateway.execute();

        verify(actorEntityRepository).findAllByOrderByIdAsc();
        assertThat(actors).isNotNull().isNotEmpty().hasSize(2);
    }
}