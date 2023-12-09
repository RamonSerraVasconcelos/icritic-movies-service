package com.icritic.movies.dataprovider.database.mapper;

import com.icritic.movies.core.model.Actor;
import com.icritic.movies.core.usecase.fixture.ActorFixture;
import com.icritic.movies.dataprovider.database.entity.ActorEntity;
import com.icritic.movies.dataprovider.database.fixture.ActorEntityFixture;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ActorEntityMapperTest {

    @Test
    void givenActor_thenReturnActorEntity() {
        Actor actor = ActorFixture.load();

        ActorEntity actorEntity = ActorEntityMapper.INSTANCE.actorToActorEntity(actor);

        assertThat(actorEntity).isNotNull();
        assertThat(actorEntity.getId()).isEqualTo(actor.getId());
        assertThat(actorEntity.getName()).isEqualTo(actor.getName());
        assertThat(actorEntity.getDescription()).isEqualTo(actor.getDescription());
        assertThat(actorEntity.getCountryId()).isEqualTo(actor.getCountry().getId());
    }

    @Test
    void givenActorEntity_thenReturnActor() {
        ActorEntity actorEntity = ActorEntityFixture.load();

        Actor actor = ActorEntityMapper.INSTANCE.actorEntityToActor(actorEntity);

        assertThat(actor).isNotNull();
        assertThat(actor.getId()).isEqualTo(actorEntity.getId());
        assertThat(actor.getName()).isEqualTo(actorEntity.getName());
        assertThat(actor.getDescription()).isEqualTo(actorEntity.getDescription());
        assertThat(actor.getCountry().getId()).isEqualTo(actorEntity.getCountryId());
    }
}