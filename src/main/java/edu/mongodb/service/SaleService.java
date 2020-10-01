package edu.mongodb.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;

import edu.mongodb.model.Patient;
import edu.mongodb.model.Product;
import edu.mongodb.model.Sale;
import edu.mongodb.model.SaleDetail;
import edu.mongodb.model.dto.SaleDto;
import edu.mongodb.repository.PatientRepository;
import edu.mongodb.repository.ProductRepository;
import edu.mongodb.repository.SaleRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SaleService {
	public static final String PATIENT_ID_PATH = "patientId";
	
	private Logger log = Logger.getLogger(SaleService.class.getName());
	
	private SaleRepository repository;
	private ProductRepository productRepository;
	private PatientRepository patientRepository;
	
	public SaleService(SaleRepository repository, ProductRepository productRepository, PatientRepository patientRepository) {
		this.repository = repository;
		this.productRepository = productRepository;
		this.patientRepository = patientRepository;
	}
	
	public Mono<Patient> save(ServerRequest request) {
		Flux<Sale> fluxSales = request.bodyToMono(SaleDto.class)
				.flatMapMany(p -> Flux.fromIterable(p.getProducts()))
				.flatMap(pid -> Flux.zip(
							productRepository.findById(pid.getProductId()), 
							Mono.just(pid.getQuantity()), 
							(p,q) -> createNewSales(p, q)
						)
				);
		
		
		String patientId = request.pathVariable(PATIENT_ID_PATH);
		
		Mono<Patient> monoPatient = patientRepository.findById(patientId);
		
		return Mono.zip(monoPatient, fluxSales.collectList(), (p, s) -> addSalesToPatient(p, s))
				.flatMap(p -> patientRepository.save(p));
	}
	
	public Patient addSalesToPatient(Patient p, List<Sale> sales) {
		for (Sale sale : sales) {
			p.addSale(sale);
			sale.setId(p.getSales().size());
		}
		return p;
	}
	
	
	public Sale createNewSales(Product product, Integer quantity) {
		Sale sale = new Sale();
		sale.setDate(LocalDate.now());
		sale.setTime(LocalTime.now());
		sale.setQuantity(quantity);
		sale.setTotalPrice(product.getPrice().multiply(new BigDecimal(quantity).setScale(2, RoundingMode.HALF_UP)));
		sale.setProductId(product.getId());
		
		List<SaleDetail> detail  = new ArrayList<>();
		
		for (String procedureId:product.getProcedureIds()) {
			for (int i=0; i<quantity; i++) {
				SaleDetail sd = createNewSaleDetail(procedureId, detail.size());
				detail.add(sd);
			}
		}
		
		sale.setDetail(detail);
		
		return sale;
	}
	
	public SaleDetail createNewSaleDetail(String procedureId, Integer id) {
		SaleDetail sd = new SaleDetail();
		sd.setProcedureId(procedureId);
		sd.setCompleted(false);
		sd.setId(id);
		return sd;
	}

}
