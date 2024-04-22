package com.tg.patientregistrationapi.models;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Patient")
public class Patient implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Patient_Id")
	private long patientId;
	//value object
	@Embedded
	private FullName name;
	@Column(name="DOB")
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate dob;	
	@Column(name="Gender")
	@Enumerated(EnumType.STRING)
	private Gender gender;	
	@Column(name="Occupation")
	@Enumerated(EnumType.STRING)
	private Occupation occupation;
	@Column(name="Email")
	private String email;	
	@Column(name="Mobile_No")
	private long mobileNo;

}
