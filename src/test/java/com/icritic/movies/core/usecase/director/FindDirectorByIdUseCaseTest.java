package com.icritic.movies.core.usecase.director;

import com.icritic.movies.core.model.Director;
import com.icritic.movies.core.usecase.country.FindCountryByIdBoundary;
import com.icritic.movies.core.usecase.fixture.CountryFixture;
import com.icritic.movies.core.usecase.fixture.DirectorFixture;
import com.icritic.movies.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindDirectorByIdUseCaseTest {

    @InjectMocks
    private FindDirectorByIdUseCase findDirectorByIdUseCase;

    @Mock
    private FindDirectorByIdBoundary findDirectorByIdBoundary;

    @Mock
    private FindCountryByIdBoundary findCountryByIdBoundary;

    @Test
    void givenValidParameter_thenFind_andReturnDirector() {
        Director director = DirectorFixture.load();

        when(findDirectorByIdBoundary.execute(director.getId())).thenReturn(Optional.of(director));
        when(findCountryByIdBoundary.execute(director.getCountry().getId())).thenReturn(Optional.of(CountryFixture.load()));

        Director resultDirector = findDirectorByIdUseCase.execute(director.getId());

        assertThat(resultDirector).isNotNull().usingRecursiveComparison().isEqualTo(director);
    }

    @Test
    void givenValidParameter_whenDirectorIsNotFound_thenThrowResourceNotFoundException() {
        when(findDirectorByIdBoundary.execute(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> findDirectorByIdUseCase.execute(1L));
    }
}