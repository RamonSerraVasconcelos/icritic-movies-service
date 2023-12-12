package com.icritic.movies.dataprovider.api.mapper;

import com.icritic.movies.core.model.Country;
import com.icritic.movies.dataprovider.api.entity.CountryEntityResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class CountryEntityResponseMapper {

    public static final CountryEntityResponseMapper INSTANCE = Mappers.getMapper(CountryEntityResponseMapper.class);

    public abstract Country countryEntityResponseToCountry(CountryEntityResponse countryEntityResponse);
}
