package com.icritic.movies.entrypoint.dto.actor;

import com.icritic.movies.entrypoint.dto.CountryResponseDto;
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
public class ActorResponseDto {

    private Long id;
    private String name;
    private String description;
    private CountryResponseDto country;
}
