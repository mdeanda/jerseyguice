package com.thedeanda.jerseyguice.rest;

import static javax.ws.rs.core.MediaType.TEXT_HTML;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thedeanda.jerseyguice.model.TestObject;

@Path("stuff")
public class SampleEndpoint {
	private static final Logger log = LoggerFactory
			.getLogger(SampleEndpoint.class);

	@GET
	@Produces(TEXT_HTML)
	public String getAll() {
		String html = "<h2>All stuff</h2><ul>";
		html += "<li>stuff</li>";
		html += "</ul>";
		return html;
	}

	@Path("/test")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public TestObject test(TestObject test) {
		return test;
	}
}
