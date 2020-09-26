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
public class Procedure {
	
	@Id
	@EqualsAndHashCode.Include
	private String id;
	
	//Reactive Spring Mongo is not creating the indexes.
	@Indexed(name="name_idx", unique = true) 
	private String name;
	
	//Reactive Spring Mongo is not creating the indexes.
	@Indexed(name="code_idx", unique = true) 
	private String code;
	private String description;
	
	private String clinicId;
	
	

}
