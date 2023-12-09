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

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindActorByIdGatewayTest {

    @InjectMocks
    private FindActorByIdGateway findActorByIdGateway;

    @Mock
    private ActorEntityRepository actorEntityRepository;

    @Test
    void givenActorId_whenFindActorById_thenReturnActor() {
        ActorEntity actorEntity = ActorEntityFixture.load();

        when(actorEntityRepository.findById(1L)).thenReturn(Optional.of(actorEntity));

        Optional<Actor> actor = findActorByIdGateway.execute(1L);

        verify(actorEntityRepository).findById(1L);
        assertThat(actor).isPresent();
    }
}