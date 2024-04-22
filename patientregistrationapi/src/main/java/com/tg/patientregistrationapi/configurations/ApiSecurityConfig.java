package com.tg.patientregistrationapi.configurations;




import jakarta.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.RegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.tg.patientregistrationapi.filters.JwtAuthenticationFilter;
import com.tg.patientregistrationapi.services.UserAuthService;


@Configuration
@EnableWebSecurity
public class ApiSecurityConfig  {

	@Autowired
	private UserAuthService userAuthService;

	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;

	@Autowired
	private ApiAuthenticationEntryPoint authenticationEntryPoint;

	
	
	


	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userAuthService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	 public WebSecurityCustomizer ignoringCustomizer() {
	        return (web) -> web.ignoring().requestMatchers("/signin", "/signup");
	 }

	 @Bean
	  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		 http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(authz -> authz
				 .requestMatchers("/signin", "/signup")
		 .permitAll().anyRequest()
			.authenticated())
		 .exceptionHandling((exception)-> 
		 exception.authenticationEntryPoint(authenticationEntryPoint))
		 .sessionManagement(sess -> sess.sessionCreationPolicy
				 (SessionCreationPolicy.STATELESS))  		 
	     .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

	       return http.build();
	   }



	@Bean
	public RegistrationBean jwtAuthFilterRegister(JwtAuthenticationFilter filter) {
		FilterRegistrationBean<JwtAuthenticationFilter> registrationBean = new FilterRegistrationBean<>(filter);
		registrationBean.setEnabled(false);
		return registrationBean;
	}

	 @Bean
     public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
         return authenticationConfiguration.getAuthenticationManager();
     }
 
	
		/*
		 * @Bean public AuthenticationManager authenticationManagerBean() throws
		 * Exception { return super.authenticationManagerBean(); }
		 */

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
