package edu.mongodb.service;

import java.util.logging.Logger;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;

import edu.mongodb.model.Doctor;
import edu.mongodb.repository.DoctorRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DoctorService extends GenericEntityService<Doctor>{
	protected Logger log = Logger.getLogger(DoctorService.class.getName());
	
	private DoctorRepository repository;
	
	public DoctorService(DoctorRepository patientRepository) {
		super(patientRepository, Doctor.class);
		this.repository = patientRepository;
	}
	
	public Flux<Doctor> getAll(ServerRequest request) {
		String clinicId = request.pathVariable(CLINIC_ID);
		return repository.findByClinicId(clinicId);
	}
	
	public Mono<Void> deleteAll(ServerRequest request) {	
		String id = request.pathVariable(CLINIC_ID);
		//return repository.deleteAll();
		return null;
	}
	

}
