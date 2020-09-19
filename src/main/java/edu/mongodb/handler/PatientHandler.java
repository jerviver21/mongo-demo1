package edu.mongodb.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import edu.mongodb.model.Patient;
import edu.mongodb.repository.PatientRepository;
import edu.mongodb.service.PatientService;
import reactor.core.publisher.Mono;

@Component
public class PatientHandler extends GenericHandler<Patient> {
	
	PatientService service;
	
	public PatientHandler(PatientRepository repository, PatientService service) {
		super(repository, Patient.class);
		this.service = service;
	}
	
	public Mono<ServerResponse> savePatient(ServerRequest request) {
		return service.savePatient(request).flatMap(p -> ServerResponse.status(HttpStatus.CREATED)
														.contentType(MediaType.APPLICATION_JSON)
														.body(Mono.just(p), Patient.class));		
	}
	

}
