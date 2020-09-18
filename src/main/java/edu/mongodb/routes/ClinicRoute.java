package edu.mongodb.routes;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import edu.mongodb.handler.ClinicHandler;

@Configuration
public class ClinicRoute {
	private final String CLINIC_PATH = "/clinics";
	private final String ID_PATH = "/{id}";
	
	@Bean
	RouterFunction<ServerResponse> clinicRoutes(ClinicHandler handler ) {
		//The order is important to avoid confuse {id} with a route
		return RouterFunctions
				.route(RequestPredicates.GET(CLINIC_PATH).and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), handler::getAll)
				.andRoute(RequestPredicates.POST(CLINIC_PATH).and(RequestPredicates.contentType(MediaType.APPLICATION_JSON)), handler::save)
				.andRoute(RequestPredicates.DELETE(CLINIC_PATH).and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), handler::deleteAll)
				.andRoute(RequestPredicates.GET(CLINIC_PATH+ID_PATH).and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), handler::getEntity)
				.andRoute(RequestPredicates.PUT(CLINIC_PATH+ID_PATH).and(RequestPredicates.contentType(MediaType.APPLICATION_JSON)), handler::update)
				.andRoute(RequestPredicates.DELETE(CLINIC_PATH+ID_PATH).and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), handler::delete)
				.andRoute(RequestPredicates.GET("/query").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), handler::query);
				//.andRoute(RequestPredicates.GET("/specific").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), handler::getClinicByName1)
				//.andRoute(RequestPredicates.GET("/clinicByName").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), handler::getClinicByName);
	}

}
