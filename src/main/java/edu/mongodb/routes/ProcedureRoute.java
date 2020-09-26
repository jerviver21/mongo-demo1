package edu.mongodb.routes;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import edu.mongodb.handler.ClinicHandler;
import edu.mongodb.handler.ProcedureHandler;

@Configuration
public class ProcedureRoute {
	private final String CLINIC_PATH = "/clinics";
	private final String CLINIC_ID_PATH = "/{clinicId}";
	private final String PATH = "/procedures";
	private final String ID_PATH = "/{id}";
	
	@Bean
	RouterFunction<ServerResponse> procedureRoutes(ProcedureHandler handler ) {
		//The order is important to avoid confuse {id} with a route
		return RouterFunctions
				.route(RequestPredicates.GET(CLINIC_PATH+CLINIC_ID_PATH+PATH)
						.and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), handler::getAll)
				.andRoute(RequestPredicates.POST(CLINIC_PATH+CLINIC_ID_PATH+PATH)
						.and(RequestPredicates.contentType(MediaType.APPLICATION_JSON)), handler::save)
				.andRoute(RequestPredicates.DELETE(CLINIC_PATH+CLINIC_ID_PATH+PATH)
						.and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), handler::deleteAll)
				.andRoute(RequestPredicates.GET(CLINIC_PATH+CLINIC_ID_PATH+PATH+ID_PATH)
						.and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), handler::getEntity)
				.andRoute(RequestPredicates.PUT(CLINIC_PATH+CLINIC_ID_PATH+PATH+ID_PATH)
						.and(RequestPredicates.contentType(MediaType.APPLICATION_JSON)), handler::update)
				.andRoute(RequestPredicates.DELETE(CLINIC_PATH+CLINIC_ID_PATH+PATH+ID_PATH)
						.and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), handler::delete);
	}

}
