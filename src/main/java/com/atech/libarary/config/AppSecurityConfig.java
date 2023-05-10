package com.atech.libarary.config;

import com.okta.spring.boot.oauth.Okta;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;

/**
 * @author raed abu Sa'da
 * on 01/05/2023
 */

@Configuration
public class AppSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // disable CSRF
        http.csrf().disable();

        // protect endpoints at /api/<any>/secure
        http.authorizeHttpRequests(configure -> {
                    configure.antMatchers("/api/books/secure/**")
                            .authenticated();
                }).oauth2ResourceServer()
                .jwt();

        // add CORS filters
        http.cors();

        // add content negotiation
        http.setSharedObject(ContentNegotiationStrategy.class, new HeaderContentNegotiationStrategy());

        // force a non-empty body for 401's errors
        Okta.configureResourceServer401ResponseBody(http);

        return http.build();
    }
}
