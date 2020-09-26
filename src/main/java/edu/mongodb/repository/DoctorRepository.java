package edu.mongodb.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import edu.mongodb.model.Doctor;
import reactor.core.publisher.Flux;

public interface DoctorRepository extends ReactiveCrudRepository<Doctor, String> {
	
	public Flux<Doctor> findByClinicId(String clinicId);

}
