package com.icritic.movies.entrypoint.mapper;

import com.icritic.movies.core.model.Category;
import com.icritic.movies.entrypoint.dto.category.CategoryResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class CategoryDtoMapper {

    public static final CategoryDtoMapper INSTANCE = Mappers.getMapper(CategoryDtoMapper.class);

    public abstract CategoryResponseDto categoryToCategoryResponsetDto(Category category);
}
