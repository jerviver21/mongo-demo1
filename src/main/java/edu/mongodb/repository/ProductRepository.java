package edu.mongodb.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import edu.mongodb.model.Product;

public interface ProductRepository extends ReactiveCrudRepository<Product, String> {

}
