package com.ex.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ex.domain.Badge;

public interface BadgeRepo extends JpaRepository<Badge, Integer> {
	
	

}
