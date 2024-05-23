package com.icritic.movies.dataprovider.database.repository;

import com.icritic.movies.dataprovider.database.entity.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryEntityRepository extends JpaRepository<CategoryEntity, Long>, CategoryEntityCustomRepository {

    CategoryEntity findByName(String name);

    Page<CategoryEntity> findAllByOrderByIdAsc(Pageable pageable);
}
