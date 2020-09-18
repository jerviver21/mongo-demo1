package edu.mongodb.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import edu.mongodb.model.Patient;

public interface PatientRepository extends ReactiveCrudRepository<Patient, String> {

}
