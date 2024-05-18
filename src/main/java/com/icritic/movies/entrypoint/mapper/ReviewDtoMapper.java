package com.icritic.movies.entrypoint.mapper;

import com.icritic.movies.core.model.Review;
import com.icritic.movies.entrypoint.dto.movie.ReviewCreateResponse;
import com.icritic.movies.entrypoint.dto.movie.ReviewResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class ReviewDtoMapper {

    public static final ReviewDtoMapper INSTANCE = Mappers.getMapper(ReviewDtoMapper.class);

    public abstract ReviewCreateResponse reviewToReviewCreateResponse(Review review);

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "userName", source = "user.name")
    @Mapping(target = "userProfilePictureUrl", source = "user.profilePictureUrl")
    public abstract ReviewResponseDto reviewToReviewResponseDto(Review review);
}
