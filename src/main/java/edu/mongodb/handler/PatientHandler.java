package edu.mongodb.handler;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import edu.mongodb.model.Doctor;
import edu.mongodb.model.Patient;
import edu.mongodb.service.PatientService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class PatientHandler extends GenericServiceHandler<Patient> {
	
	PatientService service;
	
	public PatientHandler(PatientService service) {
		super(service, Patient.class);
		this.service = service;
	}
	
	public Mono<ServerResponse> getAll(ServerRequest request) {
		Flux<Patient	> entitys = service.getAll(request);
		
		System.out.println(entitys);
		
		return ServerResponse.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(entitys, Doctor.class);
	}
	
	public Mono<ServerResponse> deleteAll(ServerRequest request) {	
		return ServerResponse.ok().build(service.deleteAll(request));
	}
	

}
