package com.tg.patientregistrationapi.configurations;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import java.util.stream.Collectors;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import org.springframework.stereotype.Component;

import com.tg.patientregistrationapi.exceptions.JwtTokenMalformedException;
import com.tg.patientregistrationapi.exceptions.JwtTokenMissingException;
import com.tg.patientregistrationapi.models.Role;
import com.tg.patientregistrationapi.models.User;



@Component


public class JwtUtil {

	@Value("${jwt.secret}")
	private String jwtSecret;
	//private final VaultConfiguration vaultConfiguration;
	//vault initialization
	//public JwtUtil(VaultConfiguration configuration) {
	  //  this.vaultConfiguration = configuration;
	 // }

	@Value("${jwt.token.validity}")
	private long tokenValidity;

	public User getUser(final String token) {
		try {
			Claims body = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
			User user = new User();
			user.setUserName(body.getSubject());
			List<Role> roles = Arrays.asList(body.get("roles").toString().split(",")).stream().map(r -> new Role(r))
					.collect(Collectors.toList());
			user.setRoles(roles);
			return user;
		} catch (Exception e) {
			System.out.println(e.getMessage() + " => " + e);
		}
		return null;
	}

	public String generateToken(User user) {
		Claims claims = Jwts.claims().setSubject(user.getUserName());
		claims.put("roles", user.getRoles());
		long nowMillis = System.currentTimeMillis();
		long expMillis = nowMillis + tokenValidity;
		Date exp = new Date(expMillis);
		return Jwts.builder().setClaims(claims).setIssuedAt(new Date(nowMillis)).setExpiration(exp)
				.signWith(SignatureAlgorithm.HS512,jwtSecret).compact();
	}

	public void validateToken(final String token) {
		try {
			System.out.println(token);
			
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
		} catch (SignatureException ex) {
			throw new JwtTokenMalformedException("Invalid JWT signature");
		} catch (MalformedJwtException ex) {
			throw new JwtTokenMalformedException("Invalid JWT token");
		} catch (ExpiredJwtException ex) {
			throw new JwtTokenMalformedException("Expired JWT token");
		} catch (UnsupportedJwtException ex) {
			throw new JwtTokenMalformedException("Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
			throw new JwtTokenMissingException("JWT claims string is empty.");
		}
	}

}
