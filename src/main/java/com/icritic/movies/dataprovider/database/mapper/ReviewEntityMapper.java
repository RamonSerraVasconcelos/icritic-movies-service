package com.icritic.movies.dataprovider.database.mapper;

import com.icritic.movies.core.model.Review;
import com.icritic.movies.dataprovider.database.entity.ReviewEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class ReviewEntityMapper {

    public static final ReviewEntityMapper INSTANCE = Mappers.getMapper(ReviewEntityMapper.class);

    @Mapping(target = "userId", source = "user.id")
    public abstract ReviewEntity reviewToReviewEntity(Review review);

    @Mapping(target = "user.id", source = "userId")
    public abstract Review reviewEntityToReview(ReviewEntity reviewEntity);
}
