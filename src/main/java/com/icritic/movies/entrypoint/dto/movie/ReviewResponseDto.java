package com.icritic.movies.entrypoint.dto.movie;

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
public class ReviewResponseDto {

    private Long id;
    private String review;
    private Long userId;
    private String userName;
    private String userProfilePictureUrl;
}
