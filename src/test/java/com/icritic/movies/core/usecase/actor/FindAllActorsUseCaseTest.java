package com.icritic.movies.core.usecase.actor;

import com.icritic.movies.core.model.Actor;
import com.icritic.movies.core.usecase.fixture.ActorFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindAllActorsUseCaseTest {

    @InjectMocks
    private FindAllActorsUseCase findAllActorsUseCase;

    @Mock
    private FindAllActorsBoundary findAllActorsBoundary;

    @Test
    void givenExecution_thenReturnAllActors() {
        List<Actor> actors = List.of(ActorFixture.load(), ActorFixture.load());

        when(findAllActorsBoundary.execute()).thenReturn(actors);

        List<Actor> result = findAllActorsUseCase.execute();

        verify(findAllActorsBoundary).execute();
        assertThat(result).isNotNull().isNotEmpty().usingRecursiveComparison().isEqualTo(actors);
    }
}