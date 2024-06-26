package com.icritic.movies.dataprovider.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntityResponse {

    private Long id;
    private String name;
    private boolean active;
    private String profilePictureUrl;
}
