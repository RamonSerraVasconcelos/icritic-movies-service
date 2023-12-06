package com.icritic.movies.entrypoint.mapper;

import com.icritic.movies.core.model.Director;
import com.icritic.movies.core.usecase.fixture.DirectorFixture;
import com.icritic.movies.entrypoint.dto.director.DirectorResponseDto;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DirectorDtoMapperTest {

    @Test
    void givenDirector_thenReturnDirectorResponseDto() {
        Director director = DirectorFixture.load();

        DirectorResponseDto directorResponseDto = DirectorDtoMapper.INSTANCE.directorToDirectorResponseDto(director);

        assertThat(directorResponseDto).isNotNull();
        assertEquals(director.getId(), directorResponseDto.getId());
        assertEquals(director.getName(), directorResponseDto.getName());
        assertEquals(director.getCountry().getId(), directorResponseDto.getCountryId());
    }
}