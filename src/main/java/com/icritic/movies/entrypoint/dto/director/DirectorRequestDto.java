package com.icritic.movies.entrypoint.dto.director;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DirectorRequestDto {

    @NotBlank(message = "Director name is required")
    @Length(min = 2, message = "Director name length must be between 2 and 75 characters long")
    private String name;

    @NotBlank(message = "Director description is required")
    @Length(min = 4, max = 255, message = "Director length must be between 4 and 255 characters long")
    private String description;

    @NotNull(message = "CountryId is required")
    private Long countryId;
}
