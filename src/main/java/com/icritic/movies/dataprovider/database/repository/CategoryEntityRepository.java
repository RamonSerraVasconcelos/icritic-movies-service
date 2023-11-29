package com.icritic.movies.dataprovider.database.repository;

import com.icritic.movies.dataprovider.database.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryEntityRepository extends JpaRepository<CategoryEntity, Long> {

    CategoryEntity findByName(String name);

    List<CategoryEntity> findAllByOrderByIdAsc();
}
