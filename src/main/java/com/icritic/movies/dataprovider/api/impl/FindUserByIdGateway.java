package com.icritic.movies.dataprovider.api.impl;

import com.icritic.movies.core.model.User;
import com.icritic.movies.core.usecase.user.FindUserByIdBoundary;
import com.icritic.movies.dataprovider.api.entity.UserEntityResponse;
import com.icritic.movies.dataprovider.api.mapper.UserEntityResponseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class FindUserByIdGateway implements FindUserByIdBoundary {

    @Value("${application.properties.services.icritic-users-service-address}")
    private String usersServiceAddress;

    public Optional<User> execute(Long id, String authorisationToken) {
        try {
            log.info("Finding user with id on users service: [{}]", id);

            WebClient webClient = WebClient.builder()
                    .baseUrl(usersServiceAddress)
                    .build();

            UserEntityResponse userEntityResponse = webClient.get()
                    .uri("/users/{id}", id)
                    .header("Authorization", "Bearer " + authorisationToken)
                    .retrieve()
                    .bodyToMono(UserEntityResponse.class)
                    .block();

            UserEntityResponseMapper mapper = UserEntityResponseMapper.INSTANCE;

            return Optional.ofNullable(userEntityResponse).map(mapper::userEntityResponseToUser);
        } catch (Exception e) {
            log.error("Error retrieving user with id: [{}] from users service", id, e);
            return Optional.empty();
        }
    }
}
