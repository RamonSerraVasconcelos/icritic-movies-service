package com.icritic.movies.dataprovider.database.repository;

import com.icritic.movies.dataprovider.database.entity.ReviewEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewEntityRepository extends JpaRepository<ReviewEntity, Long> {

    Page<ReviewEntity> findByMovieId(Pageable pageable, Long movieId);

    ReviewEntity findByMovieIdAndUserId(Long movieId, Long userId);
}
