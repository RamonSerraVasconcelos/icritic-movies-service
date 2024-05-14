package com.icritic.movies.entrypoint.dto.movie;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ReviewRequestDto {

    @NotBlank(message = "Review is required")
    @Length(min = 2, max = 2500, message = "Review length must be between 2 and 2500 characters long")
    private String review;

    @NotNull
    @Min(value = 1, message = "Invalid movie id")
    private Long movieId;
}
