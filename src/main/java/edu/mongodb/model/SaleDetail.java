package edu.mongodb.model;

import java.util.List;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SaleDetail {

	@EqualsAndHashCode.Include
	@Id
	private String id;
	
	private String procedureId;
	
	private Boolean completed;
	
	List<Appointment> appointments;
	

}
