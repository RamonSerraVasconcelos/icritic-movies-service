package com.icritic.movies.dataprovider.database.repository.impl;

import com.icritic.movies.dataprovider.database.entity.CategoryEntity;
import com.icritic.movies.dataprovider.database.repository.CategoryEntityCustomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CategoryEntityRepositoryImpl implements CategoryEntityCustomRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public List<CategoryEntity> findByMovieId(Long movieId) {
        String query = "SELECT c.id, c.name FROM movies_categories mc LEFT JOIN categories c ON mc.category_id = c.id WHERE mc.movie_id = :movieId ORDER BY id ASC";
        MapSqlParameterSource params = new MapSqlParameterSource().addValue("movieId", movieId);

        return jdbcTemplate.query(query, params, new BeanPropertyRowMapper<>(CategoryEntity.class));
    }
}
