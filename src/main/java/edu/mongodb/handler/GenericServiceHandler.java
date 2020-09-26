package edu.mongodb.handler;

import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import edu.mongodb.service.GenericEntityService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class GenericServiceHandler<T> {
	
	protected Logger log = Logger.getLogger(GenericServiceHandler.class.getName());
	protected GenericEntityService<T> service;
	private final Class<T> typeParameterClass;
	
	public GenericServiceHandler(GenericEntityService<T> service, Class<T> typeParameterClass) {
		this.service = service;
		this.typeParameterClass = typeParameterClass;
	}
	
	public Mono<ServerResponse> getEntity(ServerRequest request) {
		Mono<T> entityMono = service.getEntity(request);
		Mono<ServerResponse> notFound = ServerResponse.notFound().build();
		
		return entityMono
				.flatMap(p -> ServerResponse.ok()
							.contentType(MediaType.APPLICATION_JSON)
							.body(BodyInserters.fromValue(p)))
				.switchIfEmpty(notFound);
	}
	
	public Mono<ServerResponse> save(ServerRequest request) {
		Mono<T> entityMono = service.save(request);
		
		return entityMono.flatMap(p -> ServerResponse.status(HttpStatus.CREATED)
										.contentType(MediaType.APPLICATION_JSON)
										.body(Mono.just(p), typeParameterClass));
				
	}
	
	public Mono<ServerResponse> update(ServerRequest request) {
		Mono<T> entityMono = service.update(request);
		Mono<ServerResponse> notFound = ServerResponse.notFound().build();
		
		return entityMono
				.flatMap(p -> ServerResponse.ok()
							.contentType(MediaType.APPLICATION_JSON)
							.body(Mono.just(p), typeParameterClass))
				.switchIfEmpty(notFound);
	}
	

	public Mono<ServerResponse> delete(ServerRequest request) {
		Mono<ServerResponse> notFound = ServerResponse.notFound().build();
				
		return ServerResponse.ok().build(service.delete(request))
				.switchIfEmpty(notFound);
	}
	
}
