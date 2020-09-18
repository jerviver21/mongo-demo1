package edu.mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Doctor {
	
	@Id
	@EqualsAndHashCode.Include
	private String id;
	private String name;
	private String specialty;
	private String email;
	private String phone;
	private String skills;
	
	@DBRef
	private Clinic clinic;
	

}
