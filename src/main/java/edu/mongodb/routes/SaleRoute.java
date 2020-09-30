package edu.mongodb.routes;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import edu.mongodb.handler.SaleHandler;

@Configuration
public class SaleRoute {
	public static final String CLINIC_PATH = "/clinics";
	public static final String CLINIC_ID_PATH = "/{clinicId}";
	public static final String PATIENT_PATH = "/patients";
	public static final String PATIENT_ID_PATH = "/{patientId}";
	public static final String SALE_PATH = "/sales";
	
	@Bean
	RouterFunction<ServerResponse> saleRoutes(SaleHandler handler ) {
		//The order is important to avoid confuse {id} with a route
		return RouterFunctions
				.route(RequestPredicates.POST(CLINIC_PATH+CLINIC_ID_PATH+PATIENT_PATH+PATIENT_ID_PATH+SALE_PATH)
						.and(RequestPredicates.contentType(MediaType.APPLICATION_JSON)), handler::save);
	}
}
