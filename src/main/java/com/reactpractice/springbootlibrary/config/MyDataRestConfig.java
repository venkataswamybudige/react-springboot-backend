package com.reactpractice.springbootlibrary.config;

import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.reactpractice.springbootlibrary.entity.Book;

public class MyDataRestConfig implements RepositoryRestConfigurer {

	private String theAllowedOrigns = "http://localhost:3000";

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
		HttpMethod[] theUnsupportedActions = { HttpMethod.POST, HttpMethod.PATCH, HttpMethod.DELETE, HttpMethod.PUT };
		config.exposeIdsFor(Book.class);

		disabledHttpMethods(Book.class, config, theUnsupportedActions);
		/* Configure cors mapping */
		cors.addMapping(config.getBasePath() + "/**").allowedOrigins(theAllowedOrigns);

	}

	private void disabledHttpMethods(Class theClass, RepositoryRestConfiguration config,
			HttpMethod[] theUnsupportedActions) {
		config.getExposureConfiguration().forDomainType(theClass)
				.withItemExposure((metaData, httpMethods) -> httpMethods.disable(theUnsupportedActions))
				.withCollectionExposure((metaData, httpMethods) -> httpMethods.disable(theUnsupportedActions));
	}

}
