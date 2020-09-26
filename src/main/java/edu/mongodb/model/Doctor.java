package edu.mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
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
	private String speciality;
	private String phone;
	private String skills;
	
	//Reactive Spring Mongo is not creating the indexes.
	@Indexed(name="doctor_idx", unique = true)  
	private String email;
	
	private String clinicId;
	

}
