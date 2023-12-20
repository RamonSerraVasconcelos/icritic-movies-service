package com.icritic.movies.entrypoint.dto.movie;

import com.icritic.movies.core.model.Country;
import com.icritic.movies.entrypoint.dto.actor.ActorResponseDto;
import com.icritic.movies.entrypoint.dto.category.CategoryResponseDto;
import com.icritic.movies.entrypoint.dto.director.DirectorResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MovieResponseDto {

    private Long id;
    private String name;
    private String synopsis;
    private List<CategoryResponseDto> categories;
    private List<DirectorResponseDto> directors;
    private List<ActorResponseDto> actors;
    private Country country;
    private Long rating;
    private LocalDate releaseDate;
}
