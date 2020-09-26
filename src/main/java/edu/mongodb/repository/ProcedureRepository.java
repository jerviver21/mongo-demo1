package edu.mongodb.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import edu.mongodb.model.Procedure;
import reactor.core.publisher.Flux;

public interface ProcedureRepository extends ReactiveCrudRepository<Procedure, String> {

	public Flux<Procedure> findByClinicId(String clinicId);
	
}
