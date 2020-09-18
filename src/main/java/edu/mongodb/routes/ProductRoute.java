package edu.mongodb.routes;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import edu.mongodb.handler.ProductHandler;

@Configuration
public class ProductRoute {
	private final String PRODUCT_PATH = "/products";
	private final String ID_PATH = "/{id}";
	
	@Bean
	RouterFunction<ServerResponse> productRoutes(ProductHandler handler ) {
		//The order is important to avoid confuse {id} with a route
		return RouterFunctions
				.route(RequestPredicates.GET(PRODUCT_PATH).and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), handler::getAll)
				.andRoute(RequestPredicates.POST(PRODUCT_PATH).and(RequestPredicates.contentType(MediaType.APPLICATION_JSON)), handler::save)
				.andRoute(RequestPredicates.DELETE(PRODUCT_PATH).and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), handler::deleteAll)
				.andRoute(RequestPredicates.GET(PRODUCT_PATH+ID_PATH).and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), handler::getEntity)
				.andRoute(RequestPredicates.PUT(PRODUCT_PATH+ID_PATH).and(RequestPredicates.contentType(MediaType.APPLICATION_JSON)), handler::update)
				.andRoute(RequestPredicates.DELETE(PRODUCT_PATH+ID_PATH).and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), handler::delete);
	}


}
