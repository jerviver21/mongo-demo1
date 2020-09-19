package edu.mongodb.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
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
public class Sale {
	
	@Id
	@EqualsAndHashCode.Include
	private String id;
	
	private LocalDate date;
	private LocalTime time;
	private Integer quantity;
	private BigDecimal totalPrice;
	
	private String productId;
	
	private List<SaleDetail> detail;
	
	

}
