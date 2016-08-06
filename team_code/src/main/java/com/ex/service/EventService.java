package com.ex.service;

import org.springframework.data.domain.Page;

import com.ex.domain.Event;

public interface EventService {

	public Page<Event> getEventpage (Integer page, Integer size);
	public Event deleteEvent (String name);
	public Event updateEvent (String name);
	
}
