package com.tg.patientregistrationapi.models;

import java.io.Serializable;

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
@Table(name="Medicine")
public class Medicine implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Medicine_Id")
	private long medicineId;
	@Column(name="Medicine_Name")
	private String medicineName;
	@Column(name="Dosage")
	private String dosage;
	@Column(name="Period")
	private String period;
	
	 @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
		@JoinColumn(foreignKey = @ForeignKey(name = "Diagnosis_Id"),
	            name = "Diagnosis_Id_FK")
	    private Diagnosis diagnosis;
	
}
