package com.tg.patientregistrationapi.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tg.patientregistrationapi.models.Role;
import com.tg.patientregistrationapi.models.User;
import com.tg.patientregistrationapi.repositories.UserRepository;

@Service
public class UserService {
    @Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public User saveUser(User user)
	{
		System.out.println(user.getPassword());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return this.userRepository.save(user);
		
	}
	
	public List<User> getUsers()
	{
		return this.userRepository.findAll();
		
	}
	
	public User getUserByName(String userName)
	{
		return this.userRepository.findById(userName).orElse(null);
	}
	
	public List<Role> getRoles(String userName)
	{
		User user =this.userRepository.findById(userName).orElse(null);
		if(user!=null)
			return user.getRoles();
		else
			return null;
	}
	
}
