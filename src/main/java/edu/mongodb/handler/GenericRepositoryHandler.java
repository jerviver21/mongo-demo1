package edu.mongodb.handler;

import java.util.logging.Logger;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import edu.mongodb.utils.EntityUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class GenericRepositoryHandler<T> {
	protected final String ID = "id"; 
	
	protected Logger log = Logger.getLogger(GenericRepositoryHandler.class.getName());
	protected ReactiveCrudRepository<T, String> repository;
	private final Class<T> typeParameterClass;
	
	public GenericRepositoryHandler(ReactiveCrudRepository<T, String> repository, Class<T> typeParameterClass) {
		this.repository = repository;
		this.typeParameterClass = typeParameterClass;
	}
	
	public Mono<ServerResponse> getAll(ServerRequest request) {
		Flux<T> entitys = repository.findAll();
		
		return ServerResponse.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(entitys, typeParameterClass);
	}
	
	public Mono<ServerResponse> getEntity(ServerRequest request) {
		String id = request.pathVariable(ID);
		
		Mono<T> entityMono = repository.findById(id);
		Mono<ServerResponse> notFound = ServerResponse.notFound().build();
		
		return entityMono
				.flatMap(p -> ServerResponse.ok()
							.contentType(MediaType.APPLICATION_JSON)
							.body(BodyInserters.fromValue(p)))
				.switchIfEmpty(notFound);
	}
	
	public Mono<ServerResponse> save(ServerRequest request) {
		Mono<T> entityMono = request.bodyToMono(typeParameterClass);
		
		return entityMono.flatMap(p -> ServerResponse.status(HttpStatus.CREATED)
										.contentType(MediaType.APPLICATION_JSON)
										.body(repository.save(p), typeParameterClass));
				
	}
	
	public Mono<ServerResponse> update(ServerRequest request) {
		String id = request.pathVariable(ID);
		
		Mono<T> existingEntityMono = repository.findById(id);
		Mono<T> entityMono = request.bodyToMono(typeParameterClass);
		Mono<ServerResponse> notFound = ServerResponse.notFound().build();
		
		return entityMono
				.zipWith(existingEntityMono, (entity, existingEntity) -> EntityUtils.updateEntity(entity, existingEntity, typeParameterClass)) 
				.flatMap(p -> ServerResponse.ok()
							.contentType(MediaType.APPLICATION_JSON)
							.body(repository.save(p), typeParameterClass))
				.switchIfEmpty(notFound);
	}
	
	public Mono<ServerResponse> delete(ServerRequest request) {
		String id = request.pathVariable(ID);
		
		Mono<T> entityMono = repository.findById(id);
		Mono<ServerResponse> notFound = ServerResponse.notFound().build();
				
		return entityMono
				.flatMap(existingEntity -> ServerResponse.ok().build(repository.delete(existingEntity)))
				.switchIfEmpty(notFound);
	}
	
	public Mono<ServerResponse> deleteAll(ServerRequest request) {	
		return ServerResponse.ok().build(repository.deleteAll());
	}

	
	
	

}
