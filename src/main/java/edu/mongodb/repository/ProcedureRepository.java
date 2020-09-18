package edu.mongodb.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import edu.mongodb.model.Procedure;

public interface ProcedureRepository extends ReactiveCrudRepository<Procedure, String> {

}
