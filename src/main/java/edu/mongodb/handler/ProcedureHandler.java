package edu.mongodb.handler;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import edu.mongodb.model.Doctor;
import edu.mongodb.model.Procedure;
import edu.mongodb.service.ProcedureService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ProcedureHandler extends GenericServiceHandler<Procedure> {
	
	private ProcedureService service;
	
	public ProcedureHandler(ProcedureService service) {
		super(service, Procedure.class);
		this.service = service;
	}
	
	public Mono<ServerResponse> getAll(ServerRequest request) {
		Flux<Procedure> entitys = service.getAll(request);
		
		return ServerResponse.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(entitys, Doctor.class);
	}
	
	public Mono<ServerResponse> deleteAll(ServerRequest request) {	
		return ServerResponse.ok().build(service.deleteAll(request));
	}
	
}
