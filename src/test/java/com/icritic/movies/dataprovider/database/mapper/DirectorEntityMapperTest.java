package com.icritic.movies.dataprovider.database.mapper;

import com.icritic.movies.core.model.Director;
import com.icritic.movies.core.usecase.fixture.DirectorFixture;
import com.icritic.movies.dataprovider.database.entity.DirectorEntity;
import com.icritic.movies.dataprovider.database.fixture.DirectorEntityFixture;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DirectorEntityMapperTest {

    @Test
    void givenDirector_thenReturnDirectorEntity() {
        Director director = DirectorFixture.load();

        DirectorEntity directorEntity = DirectorEntityMapper.INSTANCE.directorToDirectorEntity(director);

        assertThat(directorEntity).isNotNull();
        assertEquals(director.getId(), directorEntity.getId());
        assertEquals(director.getName(), directorEntity.getName());
        assertEquals(director.getCountry().getId(), directorEntity.getCountryId());
    }

    @Test
    void givenDirectorEntity_thenReturnDirector() {
        DirectorEntity directorEntity = DirectorEntityFixture.load();

        Director director = DirectorEntityMapper.INSTANCE.directorEntityToDirector(directorEntity);

        assertThat(director).isNotNull();
        assertEquals(directorEntity.getId(), director.getId());
        assertEquals(directorEntity.getName(), director.getName());
        assertEquals(directorEntity.getCountryId(), director.getCountry().getId());
    }
}