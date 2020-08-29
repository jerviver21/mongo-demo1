package edu.mongodb.domain;

import java.util.Collection;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Clinic {
	
	@Id
	private String id;
	private String name;
	
	@DBRef
	private Collection<Doctor> doctors;
	
	public Clinic(String id, String name) {
		this.id = id;
		this.name = name;
	}
	

}
