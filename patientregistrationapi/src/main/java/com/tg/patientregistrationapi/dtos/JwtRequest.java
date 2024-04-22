package com.tg.patientregistrationapi.dtos;

import lombok.Data;

//DTO
@Data
public class JwtRequest {

	private String userName;
	private String userPwd;
	
	
}
