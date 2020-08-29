package edu.mongodb.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import edu.mongodb.domain.Clinic;
import reactor.core.publisher.Mono;

public interface ClinicRepository extends ReactiveCrudRepository<Clinic, String> {
	
	public Mono<Clinic> findByName(String name);
	
	
	@Query("{ 'name': ?0 }")
	public Mono<Clinic> findSpecific(String name);

}
