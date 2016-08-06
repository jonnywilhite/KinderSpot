package com.ex.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ex.domain.User;

public interface TeacherRepo extends JpaRepository<User, Integer> {
	
	User findById(int id);

}
