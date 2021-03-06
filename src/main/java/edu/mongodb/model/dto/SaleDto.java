package edu.mongodb.model.dto;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class SaleDto {
	
	private Collection<SaleTupleDto> products;

}


