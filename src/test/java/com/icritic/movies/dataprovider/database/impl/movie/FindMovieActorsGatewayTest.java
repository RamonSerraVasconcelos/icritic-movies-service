package com.icritic.movies.dataprovider.database.impl.movie;

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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindMovieActorsGatewayTest {

    @InjectMocks
    private FindMovieActorsGateway findMovieActorsGateway;

    @Mock
    private ActorEntityRepository actorEntityRepository;

    @Test
    void givenExecution_thenReturnActors() {
        List<ActorEntity> actorsEntities = List.of(ActorEntityFixture.load(), ActorEntityFixture.load());

        when(actorEntityRepository.findByMovieId(1L)).thenReturn(actorsEntities);

        List<Actor> actors = findMovieActorsGateway.execute(1L);

        verify(actorEntityRepository).findByMovieId(1L);
        assertThat(actors).usingRecursiveComparison().ignoringFields("country").isEqualTo(actorsEntities);
    }
}