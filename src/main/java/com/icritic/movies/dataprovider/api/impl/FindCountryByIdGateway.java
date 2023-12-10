package com.icritic.movies.dataprovider.api.impl;

import com.icritic.movies.core.model.Country;
import com.icritic.movies.core.usecase.country.FindCountryByIdBoundary;
import com.icritic.movies.dataprovider.api.entity.CountryEntityResponse;
import com.icritic.movies.dataprovider.api.mapper.CountryEntityResponseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class FindCountryByIdGateway implements FindCountryByIdBoundary {

    @Value("${application.properties.services.icritic-users-service-address}")
    private String usersServiceAddress;

    public Optional<Country> execute(Long id) {
        try {
            WebClient webClient = WebClient.builder()
                    .baseUrl(usersServiceAddress)
                    .build();

            CountryEntityResponse countryEntityResponse = webClient.get()
                    .uri("/countries/{id}", id)
                    .retrieve()
                    .bodyToMono(CountryEntityResponse.class)
                    .block();

            CountryEntityResponseMapper mapper = CountryEntityResponseMapper.INSTANCE;

            return Optional.ofNullable(countryEntityResponse).map(mapper::countryEntityResponseToCountry);
        } catch (Exception e) {
            log.error("Error retrieving country with id: [{}] from users service", id, e);
            return Optional.empty();
        }
    }
}
