package edu.mongodb.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import edu.mongodb.model.Appointment;

public interface AppointmentRepository extends ReactiveCrudRepository<Appointment, String> {

}
