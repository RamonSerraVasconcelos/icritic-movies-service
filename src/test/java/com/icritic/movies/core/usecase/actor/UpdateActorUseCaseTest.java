package com.icritic.movies.core.usecase.actor;

import com.icritic.movies.core.model.Actor;
import com.icritic.movies.core.usecase.fixture.ActorFixture;
import com.icritic.movies.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateActorUseCaseTest {

    @InjectMocks
    private UpdateActorUseCase updateActorUseCase;

    @Mock
    private FindActorByIdBoundary findActorByIdBoundary;

    @Mock
    private SaveActorBoundary saveActorBoundary;

    @Test
    void givenValidParameters_thenUpdate_andReturnUpdatedActor() {
        Actor actor = ActorFixture.load();

        when(findActorByIdBoundary.execute(actor.getId())).thenReturn(Optional.of(actor));
        when(saveActorBoundary.execute(actor)).thenReturn(actor);

        Actor updatedActor = updateActorUseCase.execute(actor.getId(), actor.getName(), actor.getDescription(), actor.getCountry().getId());

        verify(saveActorBoundary).execute(actor);
        assertThat(updatedActor).isNotNull();
    }

    @Test
    void givenValidParameters_whenActorIsNotFound_thenThrowResourceNotFoundException() {
       when(findActorByIdBoundary.execute(1L)).thenReturn(Optional.empty());

       assertThrows(ResourceNotFoundException.class, () -> updateActorUseCase.execute(1L, "name", "description", 1L));
    }
}