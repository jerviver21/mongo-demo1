package edu.mongodb.service;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;

import edu.mongodb.model.Patient;
import edu.mongodb.repository.PatientRepository;
import reactor.core.publisher.Mono;

@Service
public class PatientService {
	protected Logger log = Logger.getLogger(PatientService.class.getName());
	protected final String CLINIC_ID = "clinicId"; 
	
	private PatientRepository patientRepository;
	
	public PatientService(PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}
	
	
	public Mono<Patient> savePatient(ServerRequest request) {
		Mono<Patient> entityMono = request.bodyToMono(Patient.class);
		
		return entityMono
				.map(p -> createNewPatient(p, request.pathVariable(CLINIC_ID)))
				.flatMap(p -> patientRepository.save(p));
	}
	
	
	public Patient createNewPatient(Patient p, String clinicId) {
		Patient newPatient = p;
		try {
			newPatient = (Patient)BeanUtils.cloneBean(p);
			newPatient.setClinicId(clinicId);
		} catch (Exception e) {
			log.log(Level.SEVERE, "Error cloning entity ");
		}
		return newPatient;
	}

}
