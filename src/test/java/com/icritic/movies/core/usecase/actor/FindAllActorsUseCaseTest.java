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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindAllActorsUseCaseTest {

    @InjectMocks
    private FindAllActorsUseCase findAllActorsUseCase;

    @Mock
    private FindAllActorsBoundary findAllActorsBoundary;

    @Mock
    private Pageable pageable;

    @Mock
    private Page<Actor> pageableActors;

    @Test
    void givenExecution_thenReturnAllActors() {
        when(findAllActorsBoundary.execute(pageable)).thenReturn(pageableActors);

        Page<Actor> result = findAllActorsUseCase.execute(pageable);

        verify(findAllActorsBoundary).execute(pageable);
        assertThat(result).isNotNull();
    }
}