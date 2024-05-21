package com.icritic.movies.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static java.util.Objects.isNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class MovieFilter {

    private String name;
    private List<Integer> categories;
    private List<Integer> directors;
    private List<Integer> actors;
    private Pageable pageable;

    public boolean isCacheable() {
        return isNull(categories) && isNull(directors) && isNull(actors) && isNull(name);
    }
}
