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
	private final String SALE_PATH = "/sales";
	private final String ID_PATH = "/{id}";
	
	@Bean
	RouterFunction<ServerResponse> saleRoutes(SaleHandler handler ) {
		//The order is important to avoid confuse {id} with a route
		return RouterFunctions
				.route(RequestPredicates.GET(SALE_PATH).and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), handler::getAll)
				.andRoute(RequestPredicates.POST(SALE_PATH).and(RequestPredicates.contentType(MediaType.APPLICATION_JSON)), handler::save)
				.andRoute(RequestPredicates.DELETE(SALE_PATH).and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), handler::deleteAll)
				.andRoute(RequestPredicates.GET(SALE_PATH+ID_PATH).and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), handler::getEntity)
				.andRoute(RequestPredicates.PUT(SALE_PATH+ID_PATH).and(RequestPredicates.contentType(MediaType.APPLICATION_JSON)), handler::update)
				.andRoute(RequestPredicates.PATCH(SALE_PATH+ID_PATH).and(RequestPredicates.contentType(MediaType.APPLICATION_JSON)), handler::patch)
				.andRoute(RequestPredicates.DELETE(SALE_PATH+ID_PATH).and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), handler::delete);
	}

}
