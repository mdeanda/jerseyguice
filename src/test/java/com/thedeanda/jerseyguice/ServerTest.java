package com.thedeanda.jerseyguice;

import java.io.IOException;
import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.core.spi.component.ioc.IoCComponentProviderFactory;
import com.sun.jersey.guice.spi.container.GuiceComponentProviderFactory;
import com.thedeanda.jerseyguice.config.ConfigModule;

public class ServerTest {
	static final URI BASE_URI = getBaseURI();
	private HttpServer server;

	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost/").port(8080).build();
	}

	@Before
	public void startServer() throws IOException, InterruptedException {
		Injector injector = Guice.createInjector(new ConfigModule());

		ResourceConfig rc = ConfigModule.getResourceConfig();
		IoCComponentProviderFactory ioc = new GuiceComponentProviderFactory(rc,
				injector);
		server = GrizzlyServerFactory.createHttpServer(BASE_URI + "api/", rc,
				ioc);
	}

	@After
	public void stopServer() {
		server.stop();
	}

	public static void main(String[] args) throws Exception {
		ServerTest test = new ServerTest();
		test.startServer();
		System.in.read(); // hit enter to stop the server
		test.server.stop();
	}
}
