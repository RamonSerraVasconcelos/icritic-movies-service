package com.icritic.movies.config;

import com.icritic.movies.entrypoint.validation.AuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<AuthorizationFilter> authenticationFilter() {

        FilterRegistrationBean<AuthorizationFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new AuthorizationFilter());
        registrationBean.addUrlPatterns("*");

        return registrationBean;
    }
}
