package edu.mongodb.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import edu.mongodb.model.Doctor;

public interface DoctorRepository extends ReactiveCrudRepository<Doctor, String> {

}
