package edu.mongodb.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Document
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {
	
	@Id
	private String id;
	private String name;
	private String specialty;

}
