package edu.mongodb.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import edu.mongodb.model.Sale;

public interface SaleRepository extends ReactiveCrudRepository<Sale, String> {

}
