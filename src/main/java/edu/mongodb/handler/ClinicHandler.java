package edu.mongodb.handler;

import java.time.Duration;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import edu.mongodb.domain.Clinic;
import edu.mongodb.repository.ClinicRepository;
import edu.mongodb.service.ClinicService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ClinicHandler {
	
	private ClinicRepository repository;
	private ClinicService service;
	
	public ClinicHandler(ClinicRepository repository, ClinicService service) {
		this.repository = repository;
		this.service = service;
	}
	
	public Mono<ServerResponse> getAllClinics(ServerRequest request) {
		Flux<Clinic> clinics = repository.findAll();
		
		return ServerResponse.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(clinics, Clinic.class);
	}
	
	public Mono<ServerResponse> query(ServerRequest request) {
		Flux<Clinic> clinics =  service.findAll();
		
		return ServerResponse.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(clinics, Clinic.class);
	}
	
	
	
	public Mono<ServerResponse> getClinic(ServerRequest request) {
		String id = request.pathVariable("id");
		
		Mono<Clinic> clinicMono = repository.findById(id);
		Mono<ServerResponse> notFound = ServerResponse.notFound().build();
		
		return clinicMono
				.flatMap(p -> ServerResponse.ok()
							.contentType(MediaType.APPLICATION_JSON)
							.body(BodyInserters.fromValue(p))
							)
				.switchIfEmpty(notFound);
	}
	
	public Mono<ServerResponse> getClinicByName(ServerRequest request) {
		String name = request.queryParam("name").get();
		
		Mono<Clinic> clinicMono = repository.findByName(name);
		Mono<ServerResponse> notFound = ServerResponse.notFound().build();
		
		return clinicMono
				.flatMap(p -> ServerResponse.ok()
							.contentType(MediaType.APPLICATION_JSON)
							.body(BodyInserters.fromValue(p))
							)
				.switchIfEmpty(notFound);
	}
	
	public Mono<ServerResponse> getClinicByName1(ServerRequest request) {
		String name = request.queryParam("name").get();
		
		Mono<Clinic> clinicMono = repository.findSpecific(name);
		Mono<ServerResponse> notFound = ServerResponse.notFound().build();
		
		return clinicMono
				.flatMap(p -> ServerResponse.ok()
							.contentType(MediaType.APPLICATION_JSON)
							.body(BodyInserters.fromValue(p))
							)
				.switchIfEmpty(notFound);
	}
	
	public Mono<ServerResponse> saveClinic(ServerRequest request) {
		Mono<Clinic> clinicMono = request.bodyToMono(Clinic.class);
		
		return clinicMono.flatMap(p -> 
			ServerResponse.status(HttpStatus.CREATED)
			.contentType(MediaType.APPLICATION_JSON)
			.body(repository.save(p), Clinic.class));
				
	}
	
	public Mono<ServerResponse> updateClinic(ServerRequest request) {
		String id = request.pathVariable("id");
		
		Mono<Clinic> existingClinicMono = repository.findById(id);
		Mono<Clinic> clinicMono = request.bodyToMono(Clinic.class);
		Mono<ServerResponse> notFound = ServerResponse.notFound().build();
		
		return clinicMono
				.zipWith(existingClinicMono, (clinic, existingClinic) -> new Clinic(existingClinic.getId(), clinic.getName())) 
				.flatMap(p -> ServerResponse.ok()
							.contentType(MediaType.APPLICATION_JSON)
							.body(repository.save(p), Clinic.class)
							)
				.switchIfEmpty(notFound);
	}
	
	public Mono<ServerResponse> deleteClinic(ServerRequest request) {
		String id = request.pathVariable("id");
		
		Mono<Clinic> clinicMono = repository.findById(id);
		Mono<ServerResponse> notFound = ServerResponse.notFound().build();
				
		return clinicMono
				.flatMap(existingClinic -> ServerResponse.ok().build(repository.delete(existingClinic)))
				.switchIfEmpty(notFound);
	}
	
	public Mono<ServerResponse> deleteAll(ServerRequest request) {	
		return ServerResponse.ok().build(repository.deleteAll());
	}
	
}
