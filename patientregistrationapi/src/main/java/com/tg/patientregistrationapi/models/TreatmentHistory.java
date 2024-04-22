package com.tg.patientregistrationapi.models;

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
@Table(name="TreatmentHistory")
public class TreatmentHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="History_Id")
	private long historyId;
	
	 @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
		@JoinColumn(foreignKey = @ForeignKey(name = "Patient_Treatemnt_Id"),
	            name = "Patient_Treatment_Id_FK")
	    private Patient patient;

}
