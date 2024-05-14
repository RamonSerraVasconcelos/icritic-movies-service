package com.icritic.movies.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Review {

    private Long id;
    private Movie movie;
    private Long userId;
    private String review;
    private LocalDateTime createdAt;
}
