package edu.mongodb.model;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class History {
	
	@Id
	@EqualsAndHashCode.Include
	private String id;
	
	private String saleId;
	
	private String detailId;
	
	private String appointmentId;
	
	private String information;
	
	private DentalMap dentalMap;
	
	
	
	

}
