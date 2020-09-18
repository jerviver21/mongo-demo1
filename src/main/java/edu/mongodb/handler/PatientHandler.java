package edu.mongodb.handler;

import org.springframework.stereotype.Component;

import edu.mongodb.model.Patient;
import edu.mongodb.repository.PatientRepository;

@Component
public class PatientHandler extends GenericHandler<Patient> {
	
	public PatientHandler(PatientRepository repository) {
		super(repository, Patient.class);
	}
	
}
