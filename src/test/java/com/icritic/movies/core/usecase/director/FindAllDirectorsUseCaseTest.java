package com.icritic.movies.core.usecase.director;

import com.icritic.movies.core.model.Director;
import com.icritic.movies.core.usecase.boundary.director.FindAllDirectorsBoundary;
import com.icritic.movies.core.usecase.fixture.DirectorFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindAllDirectorsUseCaseTest {

    @InjectMocks
    private FindAllDirectorsUseCase findAllDirectorUseCase;

    @Mock
    private FindAllDirectorsBoundary findAllDirectorsBoundary;

    @Test
    void givenExecution_thenFind_andReturnAllDirectors() {
        List<Director> directors = List.of(DirectorFixture.load(), DirectorFixture.load());

        when(findAllDirectorsBoundary.execute()).thenReturn(directors);

        List<Director> resultDirectors = findAllDirectorUseCase.execute();

        assertThat(resultDirectors).isNotNull().hasSize(2);
    }
}