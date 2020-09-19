package edu.mongodb.model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

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
public class Appointment {
	
	@Id
	@EqualsAndHashCode.Include
	private String id;
	
	private String doctorId;
	
	private Patient patientId;
	
	private LocalDate date;
	private LocalTime time;
	private Duration duration;
	private String calendarEventId;
	
	private Checkin checkin;
	

}
