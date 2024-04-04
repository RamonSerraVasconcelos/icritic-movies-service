package com.icritic.movies.entrypoint.dto.movie;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RateMovieRequestDto {

    @Min(value = 1, message = "Rate value must be between 1 and 10")
    @Max(value = 10, message = "Rate value must be between 1 and 10")
    private int rate;
}
