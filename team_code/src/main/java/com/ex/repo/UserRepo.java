package com.ex.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ex.domain.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	
	List<User> findByEmailAndPassword(String email, String password);
	
	List<User> findByEmail(String email);

}
