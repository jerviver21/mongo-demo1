package edu.mongodb.model.dto;

import java.util.Collection;

import org.springframework.data.mongodb.core.mapping.Document;

import edu.mongodb.model.Sale;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SaleDto {
	private Collection<String> productIds;
	private Collection<Sale> sales;
}
