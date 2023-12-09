package com.icritic.movies.dataprovider.database.impl.actor;

import com.icritic.movies.core.model.Actor;
import com.icritic.movies.core.usecase.fixture.ActorFixture;
import com.icritic.movies.dataprovider.database.entity.ActorEntity;
import com.icritic.movies.dataprovider.database.fixture.ActorEntityFixture;
import com.icritic.movies.dataprovider.database.repository.ActorEntityRepository;
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
class SaveActorGatewayTest {

    @InjectMocks
    private SaveActorGateway saveActorGateway;

    @Mock
    private ActorEntityRepository actorEntityRepository;

    @Test
    void givenActor_whenSaveActor_thenReturnActor() {
        Actor actor = ActorFixture.load();
        ActorEntity actorEntity = ActorEntityFixture.load();

        when(actorEntityRepository.save(any(ActorEntity.class))).thenReturn(actorEntity);

        Actor savedActor = saveActorGateway.execute(actor);

        verify(actorEntityRepository).save(any(ActorEntity.class));
        assertThat(savedActor).isNotNull();
    }
}