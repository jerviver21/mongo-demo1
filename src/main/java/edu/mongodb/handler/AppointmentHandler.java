package edu.mongodb.handler;

import org.springframework.stereotype.Component;

import edu.mongodb.model.Appointment;
import edu.mongodb.repository.AppointmentRepository;

@Component
public class AppointmentHandler extends GenericHandler<Appointment> {
	
	public AppointmentHandler(AppointmentRepository repository) {
		super(repository, Appointment.class);
	}
	
}
