package com.tg.patientregistrationapi.models;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Address")
public class Address implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Address_Id")
	private long addressId;
    @Column(name="Door_No",length = 5,nullable = false)
	private String doorNo;
    @Column(name="Street_Name",length = 75,nullable = false)
	private String streetName;
    @Column(name="City",length = 50,nullable = false)
	private String city;
    @Column(name="State",length = 50,nullable = false)
	private String state;
    @Column(name="Country",length = 50,nullable = false)
	private String country;
    @Column(name="Postal_Code")
	private long postalCode;
    @Column(name="Longitude",length = 10,nullable = false)
	private String longitude;
    @Column(name="Latitude",length = 10,nullable = false)
	private String latitude;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(foreignKey = @ForeignKey(name = "Patient_Id"),
            name = "Patient_Id_FK")
    private Patient patient;
	
}
