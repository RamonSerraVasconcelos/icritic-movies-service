package com.icritic.movies.entrypoint.mapper;

import com.icritic.movies.core.model.Actor;
import com.icritic.movies.core.usecase.fixture.ActorFixture;
import com.icritic.movies.entrypoint.dto.actor.ActorResponseDto;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ActorDtoMapperTest {

    @Test
    void givenActor_thenReturnActorResponseDto() {
        Actor actor = ActorFixture.load();

        ActorResponseDto actorResponseDto = ActorDtoMapper.INSTANCE.actorToActorResponseDto(actor);

        assertThat(actorResponseDto).isNotNull();
        assertThat(actorResponseDto.getId()).isEqualTo(actor.getId());
        assertThat(actorResponseDto.getName()).isEqualTo(actor.getName());
        assertThat(actorResponseDto.getDescription()).isEqualTo(actor.getDescription());
        assertThat(actorResponseDto.getCountry().getId()).isEqualTo(actor.getCountry().getId());
        assertThat(actorResponseDto.getCountry().getName()).isEqualTo(actor.getCountry().getName());
    }
}