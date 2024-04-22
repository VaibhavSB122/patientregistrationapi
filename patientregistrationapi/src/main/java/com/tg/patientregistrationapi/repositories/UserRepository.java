package com.tg.patientregistrationapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tg.patientregistrationapi.models.User;

public interface UserRepository extends JpaRepository<User,String>{

}
