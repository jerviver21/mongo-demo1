package edu.mongodb.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Sale {
	

	@EqualsAndHashCode.Include
	private Integer id;
	
	private LocalDate date;
	private LocalTime time;
	private Integer quantity;
	private BigDecimal totalPrice;
	private String productId;
	
	private List<SaleDetail> detail;
	

}
