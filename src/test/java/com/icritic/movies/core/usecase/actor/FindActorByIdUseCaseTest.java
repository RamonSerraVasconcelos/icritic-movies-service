package com.icritic.movies.core.usecase.actor;

import com.icritic.movies.core.model.Actor;
import com.icritic.movies.core.usecase.country.FindCountryByIdBoundary;
import com.icritic.movies.core.usecase.fixture.ActorFixture;
import com.icritic.movies.core.usecase.fixture.CountryFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindActorByIdUseCaseTest {

    @InjectMocks
    private FindActorByIdUseCase findActorByIdUseCase;

    @Mock
    private FindActorByIdBoundary findActorByIdBoundary;

    @Mock
    private FindCountryByIdBoundary findCountryByIdBoundary;

    @Test
    void givenValidParameter_thenFind_andReturnActor(){
        Actor actor = ActorFixture.load();

        when(findActorByIdBoundary.execute(any(Long.class))).thenReturn(Optional.of(actor));
        when(findCountryByIdBoundary.execute(any(Long.class))).thenReturn(Optional.of(CountryFixture.load()));

        Actor result = findActorByIdUseCase.execute(actor.getId());

        verify(findActorByIdBoundary).execute(any(Long.class));
        assertThat(result).isNotNull().usingRecursiveComparison().isEqualTo(actor);
    }
}