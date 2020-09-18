package edu.mongodb.routes;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import edu.mongodb.handler.PatientHandler;

@Configuration
public class PatientRoute {
	private final String PATIENT_PATH = "/patients";
	private final String ID_PATH = "/{id}";
	
	@Bean
	RouterFunction<ServerResponse> patientRoutes(PatientHandler handler ) {
		//The order is important to avoid confuse {id} with a route
		return RouterFunctions
				.route(RequestPredicates.GET(PATIENT_PATH).and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), handler::getAll)
				.andRoute(RequestPredicates.POST(PATIENT_PATH).and(RequestPredicates.contentType(MediaType.APPLICATION_JSON)), handler::save)
				.andRoute(RequestPredicates.DELETE(PATIENT_PATH).and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), handler::deleteAll)
				.andRoute(RequestPredicates.GET(PATIENT_PATH+ID_PATH).and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), handler::getEntity)
				.andRoute(RequestPredicates.PUT(PATIENT_PATH+ID_PATH).and(RequestPredicates.contentType(MediaType.APPLICATION_JSON)), handler::update)
				.andRoute(RequestPredicates.DELETE(PATIENT_PATH+ID_PATH).and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), handler::delete);
	}

}
