package edu.mongodb.handler;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class GenericHandler<T> {
	protected final String ID = "id"; 
	protected final String CLINIC_ID = "clinicId"; 
	
	protected Logger log = Logger.getLogger(GenericHandler.class.getName());
	protected ReactiveCrudRepository<T, String> repository;
	private final Class<T> typeParameterClass;
	
	public GenericHandler(ReactiveCrudRepository<T, String> repository, Class<T> typeParameterClass) {
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
				.zipWith(existingEntityMono, (entity, existingEntity) -> copyEntity(entity, existingEntity)) 
				.flatMap(p -> ServerResponse.ok()
							.contentType(MediaType.APPLICATION_JSON)
							.body(repository.save(p), typeParameterClass))
				.switchIfEmpty(notFound);
	}
	
	public Mono<ServerResponse> patch(ServerRequest request) {
		String id = request.pathVariable(ID);
		
		Mono<T> existingEntityMono = repository.findById(id);
		Mono<T> entityMono = request.bodyToMono(typeParameterClass);
		Mono<ServerResponse> notFound = ServerResponse.notFound().build();
		
		return entityMono
				.zipWith(existingEntityMono, (entity, existingEntity) -> patchCopyEntity(entity, existingEntity)) 
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

	//Helper Methods
	public T copyEntity(T entity, final T existingEntity) {
		T t = entity;
		try {
			ReflectionUtils.doWithFields(typeParameterClass, docField -> {
				ReflectionUtils.makeAccessible(docField);
				if (docField.getName() == "id") {
					docField.set(entity, docField.get(existingEntity));
				}
			});
			t = (T)BeanUtils.cloneBean(entity);
		} catch (Exception e) {
			log.log(Level.SEVERE, "Error cloning entity ");
		}
		return t;
	}
	
	public T patchCopyEntity(final T entity, final T existingEntity) {
		T t = entity;
		try {
			ReflectionUtils.doWithFields(typeParameterClass, docField -> {
				ReflectionUtils.makeAccessible(docField);
				final Object fieldValue = docField.get(entity);
				if (fieldValue == null) {
					docField.set(entity, docField.get(existingEntity));
				}
			});
			t = (T)BeanUtils.cloneBean(entity);
		} catch (Exception e) {
			log.log(Level.SEVERE, "Error cloning entity ");
		}
		return t;
	}
	
	

}
