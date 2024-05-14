package com.icritic.movies.entrypoint.mapper;

import com.icritic.movies.core.model.Review;
import com.icritic.movies.entrypoint.dto.movie.ReviewCreateResponse;
import com.icritic.movies.entrypoint.dto.movie.ReviewResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class ReviewDtoMapper {

    public static final ReviewDtoMapper INSTANCE = Mappers.getMapper(ReviewDtoMapper.class);

    public abstract ReviewCreateResponse reviewToReviewCreateResponse(Review review);

    public abstract ReviewResponseDto reviewToReviewResponseDto(Review review);
}
