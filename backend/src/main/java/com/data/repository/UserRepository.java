package com.data.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.data.model.User;

public interface UserRepository extends JpaRepository<User,Integer> {
	Optional<User> findByEmail(String email); // for login with email

}
