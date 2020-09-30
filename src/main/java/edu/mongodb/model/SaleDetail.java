package edu.mongodb.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SaleDetail {
	
	@EqualsAndHashCode.Include
	private Integer id;
	
	private String procedureId;
	
	private Boolean completed;
	
	List<Appointment> appointments;
	

}
