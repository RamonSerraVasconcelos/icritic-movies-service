package com.icritic.movies.dataprovider.database.repository.impl;

import com.icritic.movies.dataprovider.database.entity.DirectorEntity;
import com.icritic.movies.dataprovider.database.repository.DirectorEntityCustomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DirectorEntityRepositoryImpl implements DirectorEntityCustomRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public List<DirectorEntity> findMovieCategories(Long movieId) {
        String query = "SELECT d.id, d.name, d.country_id FROM movies_directors md LEFT JOIN directors d ON md.director_id = d.id WHERE md.movie_id = :movieId ORDER BY id ASC";
        MapSqlParameterSource params = new MapSqlParameterSource().addValue("movieId", movieId);

        return jdbcTemplate.query(query, params, new BeanPropertyRowMapper<>(DirectorEntity.class));
    }
}
