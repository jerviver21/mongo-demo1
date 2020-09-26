package edu.mongodb.service;

import java.util.logging.Logger;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;

import edu.mongodb.model.Product;
import edu.mongodb.repository.ProductRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService extends GenericEntityService<Product>{
	protected Logger log = Logger.getLogger(ProductService.class.getName());
	
	private ProductRepository repository;
	
	public ProductService(ProductRepository patientRepository) {
		super(patientRepository, Product.class);
		this.repository = patientRepository;
	}
	
	public Flux<Product> getAll(ServerRequest request) {
		String id = request.pathVariable(CLINIC_ID);
		return repository.findByClinicId(id);
	}
	
	public Mono<Void> deleteAll(ServerRequest request) {	
		String id = request.pathVariable(CLINIC_ID);
		//return repository.deleteAll();
		return null;
	}

}
