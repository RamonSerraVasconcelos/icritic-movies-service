package com.icritic.movies.core.usecase.director;

import com.icritic.movies.core.model.Director;
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
class UpdateDirectorUseCaseTest {

    @InjectMocks
    private UpdateDirectorUseCase updateDirectorUseCase;

    @Mock
    private FindDirectorByIdBoundary findDirectorByIdBoundary;

    @Mock
    private SaveDirectorBoundary saveDirectorBoundary;

    @Test
    void givenValidParameters_thenUpdate_andReturnDirector() {
        Director director = DirectorFixture.load();

        when(findDirectorByIdBoundary.execute(director.getId())).thenReturn(Optional.of(director));
        when(saveDirectorBoundary.execute(director)).thenReturn(director);

        Director returnedDirector = updateDirectorUseCase.execute(director.getId(), director.getName(), director.getDescription(), director.getCountry().getId());

        assertThat(returnedDirector).isNotNull().usingRecursiveComparison().isEqualTo(director);
    }

    @Test
    void givenValidParameters_whenDirectorNotFound_thenThrowResourceNotFoundException() {
        when(findDirectorByIdBoundary.execute(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> updateDirectorUseCase.execute(1L, "name", "description", 1L));
    }
}