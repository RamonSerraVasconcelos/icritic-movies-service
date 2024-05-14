package com.icritic.movies.dataprovider.database.mapper;

import com.icritic.movies.core.model.Review;
import com.icritic.movies.dataprovider.database.entity.ReviewEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class ReviewEntityMapper {

    public static final ReviewEntityMapper INSTANCE = Mappers.getMapper(ReviewEntityMapper.class);

    public abstract ReviewEntity reviewToReviewEntity(Review review);

    public abstract Review reviewEntityToReview(ReviewEntity reviewEntity);
}
