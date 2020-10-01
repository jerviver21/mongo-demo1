package edu.mongodb.handler;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import edu.mongodb.model.Doctor;
import edu.mongodb.model.Patient;
import edu.mongodb.service.SaleService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class SaleHandler {
	
	private SaleService service;
	
	public SaleHandler(SaleService service) {
		this.service = service;
	}
	
	public Mono<ServerResponse> save(ServerRequest request) {
		Mono<Patient> patient = service.save(request);
		Mono<ServerResponse> notFound = ServerResponse.notFound().build();
		
		
		return patient
				.flatMap(p -> ServerResponse.ok()
							.contentType(MediaType.APPLICATION_JSON)
							.body(BodyInserters.fromValue(p)))
				.switchIfEmpty(notFound);
				
	}
	
	
	
}
