package com.icritic.movies.core.usecase.director;

import com.icritic.movies.core.model.Director;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindAllDirectorsUseCaseTest {

    @InjectMocks
    private FindAllDirectorsUseCase findAllDirectorUseCase;

    @Mock
    private FindAllDirectorsBoundary findAllDirectorsBoundary;

    @Mock
    private FindAllDirectorsCachedBoundary findAllDirectorsCachedBoundary;

    @Mock
    private SaveDirectorsToCacheBoundary saveDirectorsToCacheBoundary;

    @Mock
    private Pageable pageable;

    @Mock
    private Page<Director> pageableDirectors;

    @Test
    void givenExecution_whenDirectorsAreNotOnCache_thenReturnAllDirectorsByDatabase() {
        when(findAllDirectorsCachedBoundary.execute(pageable)).thenReturn(null);
        when(findAllDirectorsBoundary.execute(pageable)).thenReturn(pageableDirectors);

        Page<Director> resultDirectors = findAllDirectorUseCase.execute(pageable);

        verify(findAllDirectorsCachedBoundary).execute(pageable);
        verify(findAllDirectorsBoundary).execute(pageable);
        verify(saveDirectorsToCacheBoundary).execute(pageableDirectors);

        assertThat(resultDirectors).isNotNull();
    }

    @Test
    void givenExecution_whenDirectorsAreOnCache_thenReturnAllDirectorsFromCache() {
        when(findAllDirectorsCachedBoundary.execute(pageable)).thenReturn(pageableDirectors);

        Page<Director> resultDirectors = findAllDirectorUseCase.execute(pageable);

        verify(findAllDirectorsCachedBoundary).execute(pageable);
        verifyNoInteractions(findAllDirectorsBoundary);
        verifyNoInteractions(saveDirectorsToCacheBoundary);

        assertThat(resultDirectors).isNotNull();
    }
}