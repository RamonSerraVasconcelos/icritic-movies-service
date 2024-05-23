package com.icritic.movies.core.usecase.fixture;

import com.icritic.movies.core.model.MovieFilter;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public class MovieFilterFixture {

    public static MovieFilter load() {
        return MovieFilter.builder()
                .pageable(PageRequest.of(0, 20))
                .build();
    }

    public static MovieFilter loadWithParams(String name, List<Integer> categories, List<Integer> directors, List<Integer> actors) {
        return MovieFilter.builder()
                .pageable(PageRequest.of(0, 20))
                .name(name)
                .categories(categories)
                .directors(directors)
                .actors(actors)
                .build();
    }
}
