package edu.mongodb.handler;

import org.springframework.stereotype.Component;

import edu.mongodb.model.Doctor;
import edu.mongodb.repository.DoctorRepository;

@Component
public class DoctorHandler extends GenericHandler<Doctor> {
	
	public DoctorHandler(DoctorRepository repository) {
		super(repository, Doctor.class);
	}
	
}
