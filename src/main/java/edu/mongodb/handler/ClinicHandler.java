package edu.mongodb.handler;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import edu.mongodb.model.Clinic;
import edu.mongodb.repository.ClinicRepository;
import edu.mongodb.service.ClinicService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ClinicHandler extends GenericHandler<Clinic>{
	
	private ClinicService service;
	
	public ClinicHandler(ClinicRepository repository, ClinicService service) {
		super(repository, Clinic.class);
		this.service = service;
	}
	
	//Test of how repositories works
	public Mono<ServerResponse> query(ServerRequest request) {
		Flux<Clinic> clinics =  service.findAll();
		
		return ServerResponse.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(clinics, Clinic.class);
	}
	
	/*public Mono<ServerResponse> getClinicByName(ServerRequest request) {
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
	}*/
	
}
