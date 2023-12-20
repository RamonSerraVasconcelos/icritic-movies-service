package com.icritic.movies.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieRequestParams {

    private String name;
    private String synopsis;
    private List<Long> categories;
    private List<Long> directors;
    private List<Long> actors;
    private Long countryId;
    private LocalDate releaseDate;
}
