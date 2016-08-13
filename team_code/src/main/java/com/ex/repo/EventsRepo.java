package com.ex.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ex.domain.Event;

public interface EventsRepo extends JpaRepository <Event, Integer> {
	
	Event findByName(String name);
	Event deleteByName (Event event);

}
