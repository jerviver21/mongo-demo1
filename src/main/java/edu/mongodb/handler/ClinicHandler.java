package edu.mongodb.handler;


import org.springframework.stereotype.Component;

import edu.mongodb.model.Clinic;
import edu.mongodb.repository.ClinicRepository;

@Component
public class ClinicHandler extends GenericRepositoryHandler<Clinic>{
	
	public ClinicHandler(ClinicRepository repository) {
		super(repository, Clinic.class);
	}
		
}
