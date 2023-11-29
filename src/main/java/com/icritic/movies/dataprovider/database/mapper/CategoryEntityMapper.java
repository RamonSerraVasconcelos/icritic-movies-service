package com.icritic.movies.dataprovider.database.mapper;

import com.icritic.movies.core.model.Category;
import com.icritic.movies.dataprovider.database.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class CategoryEntityMapper {

    public static final CategoryEntityMapper INSTANCE = Mappers.getMapper(CategoryEntityMapper.class);

    public abstract CategoryEntity categoryToCategoryEntity(Category category);

    public abstract Category categoryEntityToCategory(CategoryEntity categoryEntity);
}
