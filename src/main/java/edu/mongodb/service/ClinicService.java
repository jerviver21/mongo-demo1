package edu.mongodb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import edu.mongodb.domain.Clinic;
import reactor.core.publisher.Flux;

@Service
public class ClinicService {
	
	@Autowired
    ReactiveMongoTemplate template;

    public Flux<Clinic> findAll() {
    	
    	Query query = Query.query(new Criteria());
    	
        return template.find(query, Clinic.class);
    } 

}
