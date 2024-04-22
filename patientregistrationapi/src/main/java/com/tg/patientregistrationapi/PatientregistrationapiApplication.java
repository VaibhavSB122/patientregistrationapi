package com.tg.patientregistrationapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
@EnableCaching
public class PatientregistrationapiApplication {

	public static void main(String[] args) {
		log.info("API Loading..........");
		SpringApplication.run(PatientregistrationapiApplication.class, args);
	}

}
