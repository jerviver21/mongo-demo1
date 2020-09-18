package edu.mongodb.handler;

import org.springframework.stereotype.Component;

import edu.mongodb.model.Product;
import edu.mongodb.repository.ProductRepository;

@Component
public class ProductHandler extends GenericHandler<Product> {
	
	public ProductHandler(ProductRepository repository) {
		super(repository, Product.class);
	}
	
}
