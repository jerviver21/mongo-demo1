package edu.mongodb.model;

import java.util.ArrayList;
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
public class Patient {
	
	@Id
	@EqualsAndHashCode.Include
	private String id;
	private String name;
	
	private String phone;
	
	//Reactive Spring Mongo is not creating the indexes.
	@Indexed(name="patient_nid_idx", unique = true) 
	private String nid;
	
	//Reactive Spring Mongo is not creating the indexes.
	@Indexed(name="patient_email_idx", unique = true) 
	private String email;
	
	private List<History> history;
	
	private List<Sale> sales;
	
	private DentalMap dentalMap;
	
	private String clinicId;
	
	
	public void addSale(Sale s) {
		if (sales == null) {
			sales =  new ArrayList<>();
		}
		sales.add(s);
	}
	

}
