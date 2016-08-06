package com.ex.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ex.domain.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	
	User findByEmailAndPassword(String email, String password);

}
