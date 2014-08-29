package com.thedeanda.jerseyguice.config;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Scopes;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import com.thedeanda.jerseyguice.model.TestObject;

public class ConfigModule extends ServletModule {
	private static final Logger log = LoggerFactory
			.getLogger(ConfigModule.class);

	public static ResourceConfig getResourceConfig() {
		// TODO: update resource config to match target environment
		return new PackagesResourceConfig("com.thedeanda.jerseyguice");
	}

	@Override
	protected void configureServlets() {
		// this.
		// bind(new TypeLiteral<Dao<String>>() {
		// }).to(StuffDao.class);

		bind(GuiceContainer.class);
		bind(JacksonJsonProvider.class).in(Scopes.SINGLETON);
		bind(TestObject.class);

		ResourceConfig rc = ConfigModule.getResourceConfig();
		for (Class<?> resource : rc.getClasses()) {
			log.warn(resource.getSimpleName());
			bind(resource);
		}

		serve("/api/*").with(GuiceContainer.class);
	}
}
