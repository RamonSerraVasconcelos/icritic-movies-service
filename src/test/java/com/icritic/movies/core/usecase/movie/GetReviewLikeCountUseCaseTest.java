package com.icritic.movies.core.usecase.movie;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetReviewLikeCountUseCaseTest {

    @InjectMocks
    private GetReviewLikeCountUseCase getReviewLikeCountUseCase;

    @Mock
    private FindReviewLikeCountBoundary findReviewLikeCountBoundary;

    @ParameterizedTest
    @MethodSource("provideValues")
    void givenExecution_thenFind_andReturnReviewLikeCount(Integer boundaryReturn, int valueExpected) {
        when(findReviewLikeCountBoundary.execute(1L)).thenReturn(boundaryReturn);

        int result = getReviewLikeCountUseCase.execute(1L);

        verify(findReviewLikeCountBoundary).execute(1L);
        assertThat(result).isEqualTo(valueExpected);
    }

    @Test
    void givenExecution_whenExceptionOccurs_thenThrowException() {
        doThrow(new RuntimeException()).when(findReviewLikeCountBoundary).execute(1L);

        int result = getReviewLikeCountUseCase.execute(1L);

        verify(findReviewLikeCountBoundary).execute(1L);
        assertThat(result).isEqualTo(0);
    }

    private static Stream<Arguments> provideValues() {
        return Stream.of(
                Arguments.of(100, 100),
                Arguments.of(null, 0)
        );
    }
}