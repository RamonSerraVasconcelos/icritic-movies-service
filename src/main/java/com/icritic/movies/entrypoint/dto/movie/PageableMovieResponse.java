package com.icritic.movies.entrypoint.dto.movie;

import com.icritic.movies.entrypoint.dto.Metadata;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PageableMovieResponse {

    private List<MovieResponseDto> data;
    private Metadata metadata;
}
