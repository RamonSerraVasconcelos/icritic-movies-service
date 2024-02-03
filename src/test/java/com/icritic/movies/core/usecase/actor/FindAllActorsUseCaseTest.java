package com.icritic.movies.core.usecase.actor;

import com.icritic.movies.core.model.Actor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindAllActorsUseCaseTest {

    @InjectMocks
    private FindAllActorsUseCase findAllActorsUseCase;

    @Mock
    private FindAllActorsBoundary findAllActorsBoundary;

    @Mock
    private FindAllActorsCachedBoundary findAllActorsCachedBoundary;

    @Mock
    private SaveActorsToCacheBoundary saveActorsToCacheBoundary;

    @Mock
    private Pageable pageable;

    @Mock
    private Page<Actor> pageableActors;

    @Test
    void givenExecution_whenActorsAreNotOnCache_thenReturnAllActorsByDatabase() {
        when(findAllActorsCachedBoundary.execute(pageable)).thenReturn(null);
        when(findAllActorsBoundary.execute(pageable)).thenReturn(pageableActors);

        Page<Actor> result = findAllActorsUseCase.execute(pageable);

        verify(findAllActorsCachedBoundary).execute(pageable);
        verify(findAllActorsBoundary).execute(pageable);
        verify(saveActorsToCacheBoundary).execute(pageableActors);

        assertThat(result).isNotNull();
    }

    @Test
    void givenExecution_whenActorsAreOnCache_thenReturnAllActorsFromCache() {
        when(findAllActorsCachedBoundary.execute(pageable)).thenReturn(pageableActors);

        Page<Actor> result = findAllActorsUseCase.execute(pageable);

        verify(findAllActorsCachedBoundary).execute(pageable);
        verifyNoInteractions(findAllActorsBoundary);
        verifyNoInteractions(saveActorsToCacheBoundary);

        assertThat(result).isNotNull();
    }
}