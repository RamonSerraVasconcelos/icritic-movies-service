package com.icritic.movies.dataprovider.database.repository;

import com.icritic.movies.dataprovider.database.entity.MovieEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface MovieEntityRepository extends JpaRepository<MovieEntity, Long>, MovieEntityCustomRepository {

    @Modifying
    @Transactional
    @Query("UPDATE MovieEntity SET rating = :rating WHERE id = :movieId")
    void updateMovieRatingById(Long movieId, int rating);
}
