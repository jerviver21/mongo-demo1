package edu.mongodb.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SaleDetail {
	
	@Id
	@EqualsAndHashCode.Include
	private String id;
	
	private String procedureId;
	
	private Boolean completed;
	
	List<Appointment> appointments;
	

}
