package edu.mongodb.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import edu.mongodb.model.Patient;
import reactor.core.publisher.Flux;

public interface PatientRepository extends ReactiveCrudRepository<Patient, String> {
	
	public Flux<Patient> findByClinicId(String clinicId);

}
