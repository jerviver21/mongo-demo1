package edu.mongodb.model;

import java.util.List;

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
public class Clinic {
	
	@Id
	@EqualsAndHashCode.Include
	private String id;
	
	//Reactive Spring Mongo is not creating the indexes.
	@Indexed(name="clinic_idx", unique = true) 
	private String name;
	
	private String user;
	private String timezone;
	private String logo;
	private String clinicInformation;
	private String currency;
	private String manager;
	private String phoneNumber;
	
	List<Location> locations;
	


}
