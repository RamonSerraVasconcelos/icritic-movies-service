package com.icritic.movies.dataprovider.database.repository.impl;

import com.icritic.movies.core.model.MovieFilter;
import com.icritic.movies.dataprovider.database.entity.MovieEntity;
import com.icritic.movies.dataprovider.database.repository.MovieEntityCustomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static java.util.Objects.nonNull;

@Repository
@RequiredArgsConstructor
public class MovieEntityRepositoryImpl implements MovieEntityCustomRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private static final String BASE_QUERY = "SELECT * FROM movies m";
    private static final String BASE_COUNT_QUERY = "SELECT COUNT(id) FROM movies m";
    private static final String LEFT_JOIN_CATEGORY = " LEFT JOIN movies_categories mc ON m.id = mc.movie_id";
    private static final String LEFT_JOIN_DIRECTOR = " LEFT JOIN movies_directors md ON m.id = md.movie_id";
    private static final String LEFT_JOIN_ACTOR = " LEFT JOIN movies_actors ma ON m.id = ma.movie_id";

    public List<MovieEntity> findByParams(MovieFilter movieFilterParams) {
        StringBuilder queryBuilder = new StringBuilder(BASE_QUERY);
        MapSqlParameterSource params = new MapSqlParameterSource();

        addLeftJoins(movieFilterParams, queryBuilder);
        addConditions(movieFilterParams, queryBuilder, params);

        String query = getFinalQuery(movieFilterParams, queryBuilder, params);

        return jdbcTemplate.query(query, params, new BeanPropertyRowMapper<>(MovieEntity.class));
    }

    public Long countMovies(MovieFilter movieFilterParams) {
        StringBuilder queryBuilder = new StringBuilder(BASE_COUNT_QUERY);
        MapSqlParameterSource params = new MapSqlParameterSource();

        addLeftJoins(movieFilterParams, queryBuilder);
        addConditions(movieFilterParams, queryBuilder, params);

        return jdbcTemplate.queryForObject(queryBuilder.toString(), params, Long.class);
    }

    private void addLeftJoins(MovieFilter movieFilterParams, StringBuilder queryBuilder) {
        if (nonNull(movieFilterParams.getCategories()) && !movieFilterParams.getCategories().isEmpty()) {
            queryBuilder.append(LEFT_JOIN_CATEGORY);
        }

        if (nonNull(movieFilterParams.getDirectors()) && !movieFilterParams.getDirectors().isEmpty()) {
            queryBuilder.append(LEFT_JOIN_DIRECTOR);
        }

        if (nonNull(movieFilterParams.getActors()) && !movieFilterParams.getActors().isEmpty()) {
            queryBuilder.append(LEFT_JOIN_ACTOR);
        }
    }

    private void addConditions(MovieFilter movieFilterParams, StringBuilder queryBuilder, MapSqlParameterSource params) {
        queryBuilder.append(" WHERE active = true");

        if (nonNull(movieFilterParams.getName())) {
            queryBuilder.append(" AND to_tsvector(m.name) @@ to_tsquery(:name)");
            params.addValue("name", movieFilterParams.getName());
        }

        if (nonNull(movieFilterParams.getCategories()) && !movieFilterParams.getCategories().isEmpty()) {
            queryBuilder.append(" AND mc.category_id IN (:categories)");
            params.addValue("categories", movieFilterParams.getCategories());
        }

        if (nonNull(movieFilterParams.getDirectors()) && !movieFilterParams.getDirectors().isEmpty()) {
            queryBuilder.append(" AND md.director_id IN (:directors)");
            params.addValue("directors", movieFilterParams.getDirectors());
        }

        if (nonNull(movieFilterParams.getActors()) && !movieFilterParams.getActors().isEmpty()) {
            queryBuilder.append(" AND ma.actor_id IN (:actors)");
            params.addValue("actors", movieFilterParams.getActors());
        }
    }

    private String getFinalQuery(MovieFilter movieFilterParams, StringBuilder queryBuilder, MapSqlParameterSource params) {
        int offset = Math.abs(movieFilterParams.getPageable().getPageNumber() * movieFilterParams.getPageable().getPageSize());
        params.addValue("pageSize", movieFilterParams.getPageable().getPageSize());
        params.addValue("offset", offset);

        queryBuilder.append(" ORDER BY m.id ASC LIMIT :pageSize OFFSET :offset");
        return queryBuilder.toString();
    }
}
