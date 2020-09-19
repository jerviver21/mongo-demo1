package edu.mongodb.model;

import java.util.List;

import org.springframework.data.annotation.Id;
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
