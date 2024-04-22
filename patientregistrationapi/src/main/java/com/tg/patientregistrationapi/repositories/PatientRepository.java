package com.tg.patientregistrationapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tg.patientregistrationapi.models.Patient;

public interface PatientRepository extends JpaRepository<Patient,Long> {

}
