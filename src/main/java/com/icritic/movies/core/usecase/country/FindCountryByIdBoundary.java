package com.icritic.movies.core.usecase.country;

import com.icritic.movies.core.model.Country;

import java.util.Optional;

public interface FindCountryByIdBoundary {

    Optional<Country> execute(Long id);
}
