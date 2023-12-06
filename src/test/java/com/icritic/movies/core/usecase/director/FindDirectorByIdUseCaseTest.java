package com.icritic.movies.core.usecase.director;

import com.icritic.movies.core.model.Director;
import com.icritic.movies.core.usecase.boundary.director.FindDirectorByIdBoundary;
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

    @Test
    void givenValidParameter_thenFind_andReturnDirector() {
        Director director = DirectorFixture.load();

        when(findDirectorByIdBoundary.execute(director.getId())).thenReturn(Optional.of(director));

        Director resultDirector = findDirectorByIdUseCase.execute(director.getId());

        assertThat(resultDirector).isNotNull().usingRecursiveComparison().isEqualTo(director);
    }

    @Test
    void givenValidParameter_whenDirectorIsNotFound_thenThrowResourceNotFoundException() {
        when(findDirectorByIdBoundary.execute(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> findDirectorByIdUseCase.execute(1L));
    }
}