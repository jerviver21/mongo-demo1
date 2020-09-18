package edu.mongodb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Location {

	private String name;
	private String city;
	private String country;
	private String state;
	private String zipcode;
	@EqualsAndHashCode.Include
	private String latitude;
	@EqualsAndHashCode.Include
	private String longitude;
	
	

}
