package com.billing.app.employees.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.billing.app.employees.entities.UserCredential;

public interface UserRegisterRepository extends JpaRepository<UserCredential, Integer>{

	Optional<UserCredential> findByName(String username);

}
