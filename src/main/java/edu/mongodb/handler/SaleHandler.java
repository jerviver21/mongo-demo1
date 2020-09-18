package edu.mongodb.handler;

import org.springframework.stereotype.Component;

import edu.mongodb.model.Sale;
import edu.mongodb.repository.SaleRepository;

@Component
public class SaleHandler extends GenericHandler<Sale> {
	
	public SaleHandler(SaleRepository repository) {
		super(repository, Sale.class);
	}
	
}
