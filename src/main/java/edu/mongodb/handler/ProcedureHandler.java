package edu.mongodb.handler;

import org.springframework.stereotype.Component;

import edu.mongodb.model.Procedure;
import edu.mongodb.repository.ProcedureRepository;

@Component
public class ProcedureHandler extends GenericHandler<Procedure> {
	
	public ProcedureHandler(ProcedureRepository repository) {
		super(repository, Procedure.class);
	}
	
}
