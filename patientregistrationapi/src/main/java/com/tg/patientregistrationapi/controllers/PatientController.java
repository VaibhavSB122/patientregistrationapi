package com.tg.patientregistrationapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tg.patientregistrationapi.dtos.ResponseWrapper;
import com.tg.patientregistrationapi.models.Patient;
import com.tg.patientregistrationapi.services.PatientService;

@RestController
@RequestMapping("/patients")
public class PatientController {
    @Autowired 
	private PatientService patientService;
    @PreAuthorize("hasRole('USER')")
    
    @PostMapping("/v1.0/")
	@CrossOrigin(allowedHeaders = "*",origins = "*", 
	methods=RequestMethod.POST)
    public ResponseEntity<ResponseWrapper> addPatient(@RequestBody 
    		Patient patient){
    	
    	Patient patientResponse=this.patientService.addPatient(patient);
    	return ResponseEntity.status(HttpStatus.OK).body(new 
    			ResponseWrapper(patientResponse));
    	
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/v1.0/")  
   	@CrossOrigin(allowedHeaders = "*",origins = "*", 
   	methods=RequestMethod.GET)
       public ResponseEntity<ResponseWrapper> getAllPatients(){
       	
    	List<Patient> patientResponse=this.patientService.getAllPatients();
    	return ResponseEntity.status(HttpStatus.OK).body(new 
    			ResponseWrapper(patientResponse));
       	
       }
    
    @PreAuthorize("hasRole('USER')")
    @GetMapping(value = "/v1.0/{patientId}",produces = { "application/json"})
   	@CrossOrigin(allowedHeaders = "*",origins = "*", 
   	methods=RequestMethod.GET)
       public ResponseEntity<ResponseWrapper> getPatientById(@PathVariable("patientId") long patientId){
       	
    	Patient patientResponse=this.patientService.getPatientById(patientId);
    	return ResponseEntity.status(HttpStatus.OK).body(new 
    			ResponseWrapper(patientResponse));
       	
       }
    
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/v1.0/{patientId}/{mobileNo}")
   	@CrossOrigin(allowedHeaders = "*",origins = "*", 
   	methods=RequestMethod.PUT)
       public ResponseEntity<ResponseWrapper> updatePatient(@PathVariable("patientId") long patientId, 
    		   @PathVariable("mobileNo") long mobileNo ){
       	
       	Patient patientResponse=this.patientService.updatePatient(patientId,mobileNo);
       	return ResponseEntity.status(HttpStatus.OK).body(new 
       			ResponseWrapper(patientResponse));
       	
       }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/v1.0/{patientId}")
   	@CrossOrigin(allowedHeaders = "*",origins = "*", 
   	methods=RequestMethod.DELETE)
       public ResponseEntity<ResponseWrapper> deletePatient(@PathVariable("patientId") long patientId){
       	
       	boolean status=this.patientService.deletePatient(patientId);
       	if(status)
       	  return ResponseEntity.status(HttpStatus.OK).body(new 
       			ResponseWrapper("Patient With this Id"+patientId+"Deleted"));
       	else
       		return ResponseEntity.status(HttpStatus.OK).body(new 
           			ResponseWrapper("Patient With this Id"+patientId+"not Deleted"));
       	
       }
}
