package com.icritic.movies.dataprovider.api.mapper;

import com.icritic.movies.core.model.User;
import com.icritic.movies.dataprovider.api.entity.UserEntityResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class UserEntityResponseMapper {

    public static final UserEntityResponseMapper INSTANCE = Mappers.getMapper(UserEntityResponseMapper.class);

    public abstract User userEntityResponseToUser(UserEntityResponse userEntityResponse);
}
