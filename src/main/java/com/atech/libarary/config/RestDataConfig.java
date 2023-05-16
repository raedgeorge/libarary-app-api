package com.atech.libarary.config;

import com.atech.libarary.entity.Book;
import com.atech.libarary.entity.Message;
import com.atech.libarary.entity.Review;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

/**
 * @author raed abu Sa'da
 * on 01/05/2023
 */

@Configuration
public class RestDataConfig implements RepositoryRestConfigurer {

    private String allowedOrigins = "http://localhost:5173";

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {

        HttpMethod[] disallowedMethods = {HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE, HttpMethod.PATCH};

        config.exposeIdsFor(Book.class);
        config.exposeIdsFor(Review.class);
        config.exposeIdsFor(Message.class);

        disableHttpMethods(Book.class, config, disallowedMethods);
        disableHttpMethods(Review.class, config, disallowedMethods);
        disableHttpMethods(Message.class, config, disallowedMethods);

        cors.addMapping(config.getBasePath() + "/**")
            .allowedOrigins(allowedOrigins);
    }

    private void disableHttpMethods(Class<?> bookClass, RepositoryRestConfiguration config, HttpMethod[] disallowedMethods) {

        config.getExposureConfiguration()
                .forDomainType(bookClass)
                .withItemExposure(((metdata, httpMethods) -> httpMethods.disable(disallowedMethods)))
                .withCollectionExposure(((metdata, httpMethods) -> httpMethods.disable(disallowedMethods)));
    }
}
