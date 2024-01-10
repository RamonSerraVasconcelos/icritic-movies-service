package com.icritic.movies.entrypoint.dto.actor;

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
public class PageableActorResponse {

    private List<ActorResponseDto> data;
    private Metadata metadata;
}
