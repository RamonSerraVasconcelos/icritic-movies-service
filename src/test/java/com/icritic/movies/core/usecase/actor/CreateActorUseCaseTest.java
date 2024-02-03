package com.icritic.movies.core.usecase.actor;

import com.icritic.movies.core.model.Actor;
import com.icritic.movies.core.usecase.fixture.ActorFixture;
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
class CreateActorUseCaseTest {

    @InjectMocks
    private CreateActorUseCase createActorUseCase;

    @Mock
    private SaveActorBoundary saveActorBoundary;

    @Mock
    private InvalidateActorsCacheBoundary invalidateActorsCacheBoundary;

    @Test
    void givenValidParameters_thenCreate_andReturnActor() {
        Actor actor = ActorFixture.load();

        when(saveActorBoundary.execute(any(Actor.class))).thenReturn(actor);

        Actor result = createActorUseCase.execute(actor.getName(), actor.getDescription(), actor.getCountry().getId());

        verify(saveActorBoundary).execute(any(Actor.class));
        verify(invalidateActorsCacheBoundary).execute();

        assertThat(result).isNotNull().usingRecursiveComparison().isEqualTo(actor);
    }
}