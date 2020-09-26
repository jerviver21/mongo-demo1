package edu.mongodb.service;

import java.util.logging.Logger;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;

import edu.mongodb.model.Procedure;
import edu.mongodb.repository.ProcedureRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProcedureService extends GenericEntityService<Procedure>{
	protected Logger log = Logger.getLogger(ProcedureService.class.getName());
	
	private ProcedureRepository repository;
	
	public ProcedureService(ProcedureRepository patientRepository) {
		super(patientRepository, Procedure.class);
		this.repository = patientRepository;
	}
	
	public Flux<Procedure> getAll(ServerRequest request) {
		String id = request.pathVariable(CLINIC_ID);
		return repository.findByClinicId(id);
	}
	
	public Mono<Void> deleteAll(ServerRequest request) {	
		String id = request.pathVariable(CLINIC_ID);
		//return repository.deleteAll();
		return null;
	}
	
}
