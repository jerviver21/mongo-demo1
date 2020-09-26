package edu.mongodb.service;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.reactive.function.server.ServerRequest;

import edu.mongodb.utils.EntityUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class GenericEntityService<T> {
	protected final String ID = "id"; 
	protected final String CLINIC_ID = "clinicId"; 
	
	protected Logger log = Logger.getLogger(GenericEntityService.class.getName());
	
	protected ReactiveCrudRepository<T, String> repository;
	private final Class<T> typeParameterClass;
	
	public GenericEntityService(ReactiveCrudRepository<T, String> repository, Class<T> typeParameterClass) {
		this.repository = repository;
		this.typeParameterClass = typeParameterClass;
	}
	
	public Mono<T> getEntity(ServerRequest request) {
		String id = request.pathVariable(ID);
		return repository.findById(id);
	}
	
	public Mono<T> save(ServerRequest request) {
		Mono<T> entityMono = request.bodyToMono(typeParameterClass);
		
		return entityMono
				.map(p -> createNewEntity(p, request.pathVariable(CLINIC_ID)))
				.flatMap(p -> repository.save(p));
	}
	
	public Mono<T> update(ServerRequest request) {
		String id = request.pathVariable(ID);
		
		Mono<T> existingEntityMono = repository.findById(id);
		Mono<T> entityMono = request.bodyToMono(typeParameterClass);

		return entityMono
				.zipWith(existingEntityMono, (entity, existingEntity) -> EntityUtils.updateEntity(entity, existingEntity, typeParameterClass)) 
				.flatMap(p -> repository.save(p));
	}
	
	public Mono<Void> delete(ServerRequest request) {
		String id = request.pathVariable(ID);
		
		Mono<T> entityMono = repository.findById(id);
				
		return entityMono
				.flatMap(existingEntity -> repository.delete(existingEntity));
	}
	
	//Helper methods
	public T createNewEntity(final T e, String clinicId) {
		T newEntity = e;
		try {
			ReflectionUtils.doWithFields(typeParameterClass, docField -> {
				ReflectionUtils.makeAccessible(docField);
				if (docField.getName() == CLINIC_ID) {
					docField.set(e, clinicId);
				}
			});
			newEntity = (T)BeanUtils.cloneBean(e);
		} catch (Exception exception) {
			log.log(Level.SEVERE, "Error creating new entity", e);
		}
		return newEntity;
	}
	


}
