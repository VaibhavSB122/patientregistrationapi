package com.tg.patientregistrationapi.models;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Diagnosis")
public class Diagnosis implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Diagnosis_Id")
	private long diagnosisId;
	
	private String diagnosisDescription;
	
	 @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
		@JoinColumn(foreignKey = @ForeignKey(name = "History_Id"),
	            name = "History_Id_FK")
	    private TreatmentHistory treatmentHistory;

}
