package com.icritic.movies.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Movie {

    private Long id;
    private String name;
    private String synopsis;
    private List<Category> categories;
    private List<Director> directors;
    private List<Actor> actors;
    private Country country;
    private Long rating;
    private LocalDate releaseDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
