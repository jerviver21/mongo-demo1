package edu.mongodb.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

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
import edu.mongodb.routes.SaleRoute;
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
				.flatMapMany(p -> Flux.fromIterable(p.getProductIds()))
				.flatMap(pid -> productRepository.findById(pid))
				.map(p -> createNewSales(p));
		
		String patientId = request.pathVariable(PATIENT_ID_PATH);
		
		Mono<Patient> monoPatient = patientRepository.findById(patientId);
		
		return Flux
				.zip(monoPatient.repeat(), fluxSales, (p, s) -> addSaleToPatient(p, s))
				.last()
				.flatMap(p -> patientRepository.save(p));
	}
	
	public Patient addSaleToPatient(Patient p, Sale s) {
		p.addSale(s);
		s.setId(p.getSales().size());
		return p;
	}
	
	
	public Sale createNewSales(Product product) {
		Sale sale = new Sale();
		sale.setDate(LocalDate.now());
		sale.setTime(LocalTime.now());
		sale.setQuantity(1);
		sale.setProductId(product.getId());
		
		List<SaleDetail> detail  = new ArrayList<>();
		
		for (int i=0; i<product.getProcedureIds().size(); i++) {
			String procedureId = product.getProcedureIds().get(i);
			SaleDetail sd = createNewSaleDetail(procedureId);
			sd.setId(i);
			detail.add(sd);
		}
		
		sale.setDetail(detail);
		
		return sale;
	}
	
	public SaleDetail createNewSaleDetail(String procedureId) {
		SaleDetail sd = new SaleDetail();
		sd.setProcedureId(procedureId);
		sd.setCompleted(false);
		return sd;
	}

}
