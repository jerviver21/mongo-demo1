package edu.mongodb.model;

import java.math.BigDecimal;
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
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product {
	
	@Id
	@EqualsAndHashCode.Include
	private String id;
	
	private String name;
	private BigDecimal price;
	private String description;
	
	@DBRef
	List<Procedure> procedures;
	

}
