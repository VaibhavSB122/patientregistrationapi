package com.tg.patientregistrationapi.services;

import java.util.List;

import com.tg.patientregistrationapi.models.Patient;

public interface PatientService {
	
	Patient addPatient(Patient patient);
	List<Patient> getAllPatients();
	Patient getPatientById(long patientId);
	Patient updatePatient(long patientId, long mobileNo);
	boolean deletePatient(long patientId);

}
