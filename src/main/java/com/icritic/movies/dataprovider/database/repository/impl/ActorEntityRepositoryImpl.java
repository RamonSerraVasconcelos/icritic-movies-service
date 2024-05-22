package com.icritic.movies.dataprovider.database.repository.impl;

import com.icritic.movies.dataprovider.database.entity.ActorEntity;
import com.icritic.movies.dataprovider.database.repository.ActorEntityCustomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ActorEntityRepositoryImpl implements ActorEntityCustomRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public List<ActorEntity> findByMovieId(Long movieId) {
        String query = "SELECT a.id, a.name, a.country_id FROM movies_actors ma LEFT JOIN actors a ON ma.actor_id = a.id WHERE ma.movie_id = :movieId ORDER BY id ASC";
        MapSqlParameterSource params = new MapSqlParameterSource().addValue("movieId", movieId);

        return jdbcTemplate.query(query, params, new BeanPropertyRowMapper<>(ActorEntity.class));
    }
}
