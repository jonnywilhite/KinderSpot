package com.ex.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ex.domain.Event;

public interface EventsRepo extends JpaRepository <Event, Integer> {
	
	Event deleteByName (Event event);
	Page <Event> findByNameOrderByDateDesc(Pageable page);

}
