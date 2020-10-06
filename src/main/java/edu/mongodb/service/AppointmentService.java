package edu.mongodb.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;

import edu.mongodb.model.Appointment;
import edu.mongodb.model.Patient;
import edu.mongodb.model.SaleDetail;
import edu.mongodb.repository.PatientRepository;
import edu.mongodb.utils.PathUtils;
import reactor.core.publisher.Mono;

@Service
public class AppointmentService {
	
	private Logger log = Logger.getLogger(SaleService.class.getName());
	
	private PatientRepository patientRepository;
	
	public AppointmentService(PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}
	
	public Mono<Patient> save(ServerRequest request) {
		Mono<Appointment> monoApp = request.bodyToMono(Appointment.class);
		
		String patientId = request.pathVariable(PathUtils.PATIENT_ID_PATH);
		
		
		Mono<Patient> monoPatient = patientRepository.findById(patientId);
		
		return Mono.zip(monoPatient, monoApp, (p, a) -> addAppointment(p, a, request))
				.flatMap(p -> patientRepository.save(p));
	}
	
	public Patient addAppointment(Patient patient, Appointment appointment, ServerRequest request) {
		String saleId = request.pathVariable(PathUtils.SALE_ID_PATH);
		String detailId = request.pathVariable(PathUtils.DETAIL_ID_PATH);
		
		
		Optional<SaleDetail> detail = patient.getSales().stream()
				.filter(s -> s.getId().equals(saleId))
				.flatMap(s -> s.getDetail().stream())
				.filter(sd -> sd.getId().equals(detailId))
				.findAny();
		
		if (detail.isPresent()) {
			List<Appointment> appointments = detail.get().getAppointments();
			if (appointments == null) {
				appointments = new ArrayList<>();
				detail.get().setAppointments(appointments);
			}
			appointment.setId(new ObjectId().toHexString());
			appointments.add(appointment);
		}
		
		return patient;
	}
	
	

}
