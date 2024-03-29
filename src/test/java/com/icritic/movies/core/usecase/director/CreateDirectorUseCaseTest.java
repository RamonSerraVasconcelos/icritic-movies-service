package com.icritic.movies.core.usecase.director;

import com.icritic.movies.core.model.Director;
import com.icritic.movies.core.usecase.fixture.DirectorFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateDirectorUseCaseTest {

    @InjectMocks
    private CreateDirectorUseCase createDirectorUseCase;

    @Mock
    private SaveDirectorBoundary saveDirectorBoundary;

    @Mock
    private InvalidateDirectorsCacheBoundary invalidateDirectorsCacheBoundary;

    @Test
    void givenValidParameters_thenCreate_andReturnDirector() {
        Director director = DirectorFixture.load();

        when(saveDirectorBoundary.execute(any(Director.class))).thenReturn(director);

        Director returnedDirector = createDirectorUseCase.execute(director.getName(), director.getDescription(), director.getCountry().getId());

        verify(saveDirectorBoundary).execute(any(Director.class));
        verify(invalidateDirectorsCacheBoundary).execute();

        assertThat(returnedDirector).isNotNull().usingRecursiveComparison().isEqualTo(director);
    }
}