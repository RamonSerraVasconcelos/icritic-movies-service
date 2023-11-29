package com.icritic.movies.entrypoint.dto.director;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DirectorResponseDto {

    private Long id;
    private String name;
    private String description;
    private Long countryId;
}
