package edu.mongodb.service;

import java.util.logging.Logger;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;

import edu.mongodb.model.Patient;
import edu.mongodb.repository.PatientRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PatientService extends GenericEntityService<Patient>{
	protected Logger log = Logger.getLogger(PatientService.class.getName());
	
	private PatientRepository repository;
	
	public PatientService(PatientRepository patientRepository) {
		super(patientRepository, Patient.class);
		this.repository = patientRepository;
	}
	
	public Flux<Patient> getAll(ServerRequest request) {
		String id = request.pathVariable(CLINIC_ID);
		return repository.findByClinicId(id);
	}
	
	public Mono<Void> deleteAll(ServerRequest request) {	
		String id = request.pathVariable(CLINIC_ID);
		//return repository.deleteAll();
		return null;
	}
}
