package com.icritic.movies.entrypoint.dto.movie;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MovieRequestDto {

    @NotBlank(message = "Movie name is required")
    @Length(min = 2, max = 75, message = "Movie name length must be between 2 and 75 characters long")
    private String name;

    @NotBlank(message = "Movie synopss is required")
    @Length(min = 2, max = 500, message = "Movie synopsis length must be between 2 and 500 characters long")
    private String synopsis;

    @NotNull
    @Size(min = 1, message = "You must provide at least 1 category")
    private List<Long> categories;

    @NotNull
    @Size(min = 1, message = "You must provide at least 1 director")
    private List<Long> directors;

    @NotNull
    @Size(min = 1, message = "You must provide at least 1 actor")
    private List<Long> actors;

    @NotNull(message = "CountryId is required")
    private Long countryId;

    @NotNull(message = "Movie release date is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;
}
